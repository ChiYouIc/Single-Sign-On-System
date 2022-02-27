package cn.cy.server.web.sso.controller;

import cn.cy.mybatis.web.controller.BaseController;
import cn.cy.server.cache.IUserCacheService;
import cn.cy.server.core.JwtHelper;
import cn.cy.server.util.CookieUtils;
import cn.cy.server.web.sso.entity.LoginParam;
import cn.cy.server.web.sso.service.ISsoService;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 17:44
 * @Description: 登陆Controller
 */
@Controller
public class SsoController extends BaseController {

    @Resource
    private JwtHelper jwtHelper;

    @Resource
    private ISsoService ssoService;

    @Resource
    private IUserCacheService userCacheService;

    /**
     * <p>登录页</p>
     * <p>访问登录页的时候，这里先去 cookie 获取 auth-key，如果存在 {@link #login(HttpSession, HttpServletResponse, LoginParam)}，
     * 即证明该用户是已登录过，所以直接使用 auth-key 到缓存（这里使用的是 redis 缓存）中获取 token 并验证其有效性；
     * <p>如果校验通过，在缓存中创建一个 code - token 键值对 {@link ISsoService#generateCode(String)}，并将 code 作为参数重定向到 originUrl 对应的目标客户端；
     * <p><b>tip：code 是用来兑换 token 的 {@link AuthController#callback(String)}</b>
     */
    @GetMapping("/")
    public String index(@CookieValue(value = "auth-key", defaultValue = StrUtil.EMPTY) String authKey, Model model, LoginParam param) {
        String originUrl = param.getOriginUrl();

        try {
            // 如果用户已经登陆过，直接重新发布 code，用于获取 token 令牌
            String token = StrUtil.isAllNotEmpty(authKey, originUrl) ? userCacheService.getAuthKeyToken(authKey) : null;
            if (StrUtil.isNotEmpty(token) && jwtHelper.isVerify(token)) {
                String[] split = originUrl.split("\\?");
                return "redirect:" + split[0] + "?code=" + ssoService.generateCode(token);
            }

            // 当前连接的用户认证无效
            userCacheService.delAuthKeyToken(authKey);
            model.addAttribute("loginParam", param);
            return "index";
        }
        // token 验证失败 { jwtHelper.isVerify(token); }
        catch (TokenExpiredException e) {
            e.printStackTrace();
            userCacheService.delAuthKeyToken(authKey);
            model.addAttribute("loginParam", new LoginParam().setOriginUrl(originUrl));
            return "index";
        }
    }

    /**
     * <p>登录接口，验证用户登录信息
     * <ol>
     *     <li>
     *         首先验证参数 param 中 originUrl（目标客户端地址，即认证来源于那个系统发起的），
     *         如果该参数为空则不符合认证要求（这个地方可以扩展，将可验证应用保存到数据库中）
     *     </li>
     *     <li>验证用户登录信息 {@link ISsoService#authentication(LoginParam)}</li>
     *     <li>
     *         当用户登录信息验证通过时，为用户创建 token {@link ISsoService#generateToken(LoginParam, HttpSession)} 作为访问凭证，
     *         并且在 Sso-Server 所在域的 Cookie 中添加 auth-key，作为 Sso-Server 一方的访问凭证（这个是实现不跨域单点实现的关键）；
     *         这里我们不直接返回 token 凭证，而是返回的一个与之对应的 code 码，code 与 token 组成键值对缓存在单点服务中，然后用 uri 传参的
     *         方式将 code 传到目标客户端，目标客户端使用这个 code 访问单点获取 token 令牌，这样的方式是为了避免 token 直接暴露。
     *     </li>
     *     <li>
     *         当用户登录信息验证不通过时，重定向访问登录页，注意这里也带了一个 code 参数，这个 code 参数的目的是标识为何访问不成功，
     *         这个可参照参数 {@link LoginParam#code} 和 {@link LoginParam#msg}
     *     </li>
     * </ol>
     */
    @PostMapping("/login")
    public String login(HttpSession session, HttpServletResponse response, @ModelAttribute LoginParam param) {
        String originUrl = param.getOriginUrl();
        // 校验密码
        if (ssoService.authentication(param) && StrUtil.isNotEmpty(originUrl)) {
            String code = ssoService.generateToken(param, session);
            CookieUtils.addCookieValue(response, "auth-key", session.getId());
            String[] split = originUrl.split("\\?");
            return "redirect:" + split[0] + "?code=" + code;
        }
        return "redirect:/?code=401&originUrl=" + param.getOriginUrl();
    }
}

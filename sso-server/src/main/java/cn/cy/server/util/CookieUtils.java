package cn.cy.server.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @Author: 友叔
 * @Date: 2021/1/10 17:01
 * @Description: cookie 工具
 */
public class CookieUtils {

    public static String getCookieValue(String cookieName) {
        Cookie[] cookies = ServletUtils.getRequest().getCookies();
        if (ArrayUtil.isNotEmpty(cookies)) {
            return Arrays.stream(cookies).filter(o -> cookieName.equals(o.getName())).findFirst().orElse(new Cookie(cookieName, null)).getValue();
        }
        return StrUtil.EMPTY;
    }

    public static void addCookieValue(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        // cookie.setDomain("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600000);
        response.addCookie(cookie);
    }
}

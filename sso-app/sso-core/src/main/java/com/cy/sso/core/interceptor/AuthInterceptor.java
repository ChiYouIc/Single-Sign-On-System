package com.cy.sso.core.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.cy.sso.core.properties.SsoProperties;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @Author: 友叔
 * @Date: 2021/1/7 21:41
 * @Description: 拦截器
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final static Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

    @Resource
    private SsoProperties ssoProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("access resource url " + request.getRequestURI());
        Cookie[] cookies = request.getCookies();
        String token = StrUtil.EMPTY;
        // 如果 cookie 不为空，尝试从中获取 token
        if (cookies != null && cookies.length != 0) {
            token = Arrays.stream(cookies).filter(o -> "Authentication-Token".equals(o.getName())).findFirst().orElse(new Cookie("Authentication-Token", "")).getValue();
        }

        // 进行单点登录验证
        if (!sendToSsoServer(token)) {
            String redirectUrl = ssoProperties.getUrl() + "?originUrl=" + ssoProperties.getOriginUrl();
            response.sendRedirect(redirectUrl);
            return false;
        }

        return true;
    }

    private boolean sendToSsoServer(String token) {
        // token 验证地址
        final String uri = ssoProperties.getUrl()
                + "auth/?token="
                + token
                + "&originUrl="
                + ssoProperties.getOriginUrl();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(uri);
            String responseBody = httpClient.execute(httpGet, httpResponse -> {
                int status = httpResponse.getStatusLine().getStatusCode();
                if (status == HttpStatus.PERMANENT_REDIRECT.value()) {
                    LOGGER.warn("sso server response permanent redirect ...");
                } else if (status < HttpStatus.OK.value() || status >= HttpStatus.MULTIPLE_CHOICES.value()) {
                    LOGGER.error("sso server source rejected access ...");
                }
                HttpEntity entity = httpResponse.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            });
            LOGGER.info(responseBody);
            Object result = JSONUtil.parseObj(responseBody).get("result");
            if (StrUtil.equals(result.toString(), "success")) {
                LOGGER.info("sso server access success ...");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}

package com.cy.sso.server.util;

import javax.servlet.http.Cookie;
import java.util.Arrays;

/**
 * @Author: 友叔
 * @Date: 2021/1/10 17:01
 * @Description: cookie 工具
 */
public class CookieUtil {

	public static String getCookieValue(String cookieName) {
		Cookie[] cookies = ServletUtils.getRequest().getCookies();
		return Arrays.stream(cookies).filter(o -> cookieName.equals(o.getName())).findFirst().orElse(new Cookie(cookieName, null)).getValue();
	}
}

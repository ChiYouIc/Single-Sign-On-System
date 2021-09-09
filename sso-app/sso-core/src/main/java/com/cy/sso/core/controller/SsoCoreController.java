package com.cy.sso.core.controller;

import com.cy.sso.core.config.properties.SsoProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: 开水白菜
 * @description: 单点认证Controller
 * @create: 2021-08-25 21:13
 **/
@RestController
@ConditionalOnProperty(prefix = "sso-core", name = "type", havingValue = "client")
public class SsoCoreController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private SsoProperties properties;

    @GetMapping("/logout")
    public String logout() {
        restTemplate.getForObject(properties.logoutUrl(), Void.class);
        return "注销成功!";
    }

    @GetMapping("/callback/{code}")
    public Map callback(@PathVariable("code") String code) {
        return restTemplate.getForObject("/callback/" + code, Map.class);
    }
}

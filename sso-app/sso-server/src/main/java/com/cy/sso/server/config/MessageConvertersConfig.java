package com.cy.sso.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author: 友叔(xu)
 * @Date: 2020/12/3 21:24
 * @Description: Spring 消息转换器配置
 */
@Configuration
public class MessageConvertersConfig implements WebMvcConfigurer {
    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;


    /**
     * 解决: 返回String类型默认使用StringHttpMessageConverter改成使用mappingJackson2HttpMessageConverter
     * <p>
     * 添加converter的第三种方式
     * <p>
     * 可以理解为是三种方式中最后执行的一种，不过这里可以通过add指定顺序来调整优先级，也可以使用remove/clear来删除converter，功能强大
     * <p>
     * 使用converters.add(xxx)会放在最低优先级（List的尾部）
     * <p>
     * 使用converters.add(0,xxx)会放在最高优先级（List的头部）
     *
     * @see <a href="https://segmentfault.com/a/1190000012658289">Springboot使用HttpMessageConverter进行http序列化和反序列化</a>
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.set(0, mappingJackson2HttpMessageConverter);
    }
}

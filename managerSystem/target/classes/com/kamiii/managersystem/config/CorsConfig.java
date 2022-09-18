package com.kamiii.managersystem.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 解决跨域问题的配置类
 */
@Configuration
public class CorsConfig{

    //设置跨域默认最大有效时长
    private final static long MAX_TIME= 60 * 60 * 24;

    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //1) 允许的域,写*则cookie无法使用
        config.addAllowedOrigin("*");
        //2) 是否发送Cookie信息
        // config.setAllowCredentials(true);
        //3) 允许的请求方式
        config.addAllowedMethod("*");
        // config.addAllowedMethod("OPTIONS");
        // config.addAllowedMethod("HEAD");
        // config.addAllowedMethod("GET");
        // config.addAllowedMethod("PUT");
        // config.addAllowedMethod("POST");
        // config.addAllowedMethod("DELETE");
        // config.addAllowedMethod("PATCH");
        // 4）允许的头信息
        config.addAllowedHeader("*");

        //设置请求最大过期时间
        config.setMaxAge(MAX_TIME);

        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        //3.返回新的CorsFilter.
        return new CorsFilter(configSource);
    }
}

package com.oauth2server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: JOSEPH.L
 * @date: 2021/10/13 2:03
 * @description:
 * @version: 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置用户源
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        // 此处我将一个简单的用户初始化在内存中
//        auth.inMemoryAuthentication()
//                // 密码编码
//                .passwordEncoder(new BCryptPasswordEncoder())
//                // 用户名
//                .withUser("user")
//                // 密码
//                .password(new BCryptPasswordEncoder().encode("123456"))
//                // 用户角色
//                .roles("USER");
    }

    /**
     * 配置网络安全
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}

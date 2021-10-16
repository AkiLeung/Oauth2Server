package com.oauth2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Demo class
 *
 * @author Joseph.l
 * @date 2021/10/31
 */

@EnableResourceServer
@EnableAuthorizationServer
@SpringBootApplication
public class Oauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }


    /**
     * 客户端验证 ：
     * http://localhost:8080/oauth/token?grant_type=client_credentials&client_id=client&client_secret=123456
     *
     * 密码验证：
     * http://localhost:8080/oauth/token?grant_type=password&username=user&password=123456
     *
     * 刷新令牌：
     * http://localhost:8080/oauth/token?grant_type=refresh_token&username=user&password=123456&refresh_token=70ed232c-361c-40f0-b722-d8115ac0d077
     * */
}

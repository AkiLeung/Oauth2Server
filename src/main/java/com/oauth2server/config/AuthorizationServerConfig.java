package com.oauth2server.config;

import com.oauth2server.config.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.annotation.Resource;

/**
 * @author: JOSEPH.L
 * @date: 2021/10/13 2:04
 * @description:
 * @version: 1.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Resource
    private MyUserDetailsService myUserDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        // 在内存中存储客户端信息
        clients.inMemory()
                // 配置客户端Id
                .withClient("client")
                // 客户端密钥 “{}”为加密方式 noop为不加密
                // 现如今Spring Security中密钥的存储格式是“{id}…………” 所以“{}”必要 否则报错：There is no PasswordEncoder mapped for the id “null”
                .secret("{noop}123456")
                // 配置授权范围 read、write、all
                .scopes("resource-server-read", "resource-server-write")
                //.scopes("all")
                // 配置该客户端支持的授权方式
                .authorizedGrantTypes("authorization_code","password","client_credentials","refresh_token","implicit")
                // token令牌有效期 单位s
                .accessTokenValiditySeconds(1800)
                // refreshtoken刷新令牌有效期 单位s 一般来说刷新令牌比令牌有效期长 便于使用刷新令牌换取令牌
                .refreshTokenValiditySeconds(3600)
                // 6、指定重定向地址 授权码认证模式和简化认证模式中必须
                .redirectUris("http://www.baidu.com");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        //5、将authenticationManager交给服务器端点
        endpoints.authenticationManager(authenticationManager);
        // 9、将myUserDetailsService交给服务器端点
        endpoints.userDetailsService(myUserDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)
            throws Exception {

        security.tokenKeyAccess(
                "isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
                .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");

    }
}

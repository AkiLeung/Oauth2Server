package com.oauth2server.config.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: JOSEPH.L
 * @date: 2021/10/15 4:16
 * @description:
 * @version: 1.0
 */
@Service("MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    /**
     * 此处加载数据库用户信息并在各种信息的校验
     * @param s
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //为了方便，我直接创建了一个用户
        //现如今Spring Security中密钥的存储格式是“{id}…………” 所以“{}”中的内容必要 详情移步官方文档
        return new User("user", "{noop}123456", AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
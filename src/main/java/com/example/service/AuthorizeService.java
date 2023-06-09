package com.example.service;

import com.example.entity.Account;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService implements UserDetailsService {
    @Resource
    UserMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null) throw new UsernameNotFoundException("不能为空");
        Account account = mapper.findAccountByNameOrEmail(username);
        if(account == null) throw new UsernameNotFoundException("密码或者用户名错误");
        return User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .roles()
                .build();
    }
}

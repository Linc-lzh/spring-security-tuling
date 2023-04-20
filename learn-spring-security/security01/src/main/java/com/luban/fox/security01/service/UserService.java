package com.luban.fox.security01.service;


import com.luban.fox.security01.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Fox
 */
public interface UserService extends UserDetailsService {

    User getByUsername(String username);
}

package com.luban.fox.security01.hander;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface MySecurityExpressionOperations {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
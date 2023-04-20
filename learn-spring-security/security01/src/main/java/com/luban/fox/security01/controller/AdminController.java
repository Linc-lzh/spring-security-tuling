package com.luban.fox.security01.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

/**
 * @author Fox
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    //@PermitAll
    @RolesAllowed("ROLE_admin")
    //@Secured("ROLE_admin")
    //@PreAuthorize("hasAuthority('admin')")
    @RequestMapping("/demo")
    public String demo(){
        String username = getUsername();
        return "spring security demo: "+username;
    }

    @PreAuthorize("#id<10")
    @RequestMapping("/index")
    public String index(int id){
        String username = getUsername();
        return "spring security demo: "+username;
    }

    private String getUsername(){
        // 获取当前登录的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username =((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

}

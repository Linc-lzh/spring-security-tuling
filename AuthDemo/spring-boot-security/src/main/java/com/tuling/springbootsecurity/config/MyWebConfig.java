package com.tuling.springbootsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    //默认Url根路径跳转到/login，此url为spring security提供
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("redirect:/login");
    }

    /**
     * 自行注入一个PasswordEncoder。
     * Security会优先从Spring容器中获取PasswordEncoder.
     * 注入一个不做任何加解密操作的密码处理器用作演示。
     * 一般常用BCryptPasswordEncoder
     * @return
     */
    @Bean
    public PasswordEncoder getPassWordEncoder(){
//        return new BCryptPasswordEncoder(10);
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 自行注入一个UserDetailsService
     * 如果没有的话，在UserDetailsServiceAutoConfiguration中会默认注入一个包含user用户的InMemoryUserDetailsManager
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(User.withUsername("admin").password("admin").authorities("mobile","salary").build()
        ,User.withUsername("manager").password("manager").authorities("salary").build()
        ,User.withUsername("worker").password("worker").roles("worker").build());
        return userDetailsManager;
//        return new JdbcUserDetailsManager(DataSource dataSource);
    }
}

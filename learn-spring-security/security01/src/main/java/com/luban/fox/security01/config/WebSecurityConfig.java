package com.luban.fox.security01.config;

import com.luban.fox.security01.hander.MyAuthenticationFailHandler;
import com.luban.fox.security01.hander.MyAuthenticationSuccessHandler;
import com.luban.fox.security01.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author Fox
 */
//@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder(){
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userServiceImpl);
//        auth.inMemoryAuthentication()
//                .withUser("fox2")
//                .password(passwordEncoder().encode("123"))
//                .authorities("admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 认证
        http.formLogin()  //表达登录
            //.usernameParameter("name")
            .loginPage("/login.html")
            .loginProcessingUrl("/user/login")
            .defaultSuccessUrl("/main.html");
            //.successForwardUrl("/tomain");
            //.successHandler(new MyAuthenticationSuccessHandler("/main.html"))
            //.failureHandler(new MyAuthenticationFailHandler("/error.html"));
            //.failureForwardUrl("/toerrer");

//        http.logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login.html");

        http.sessionManagement()
                //.invalidSessionUrl("/session/invalid")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false);

        //记住我
        http.rememberMe()
                .tokenRepository(persistentTokenRepository())//设置持久化仓库
                .tokenValiditySeconds(3600) //超时时间,单位s 默认两周
                .userDetailsService(userServiceImpl);  //设置自定义登录逻辑

        // 授权
        http.authorizeRequests()
                .antMatchers("/login.html","/user/login","/error.html","/session/invalid").permitAll()
                .anyRequest().authenticated();

        //http.csrf().disable();  // csrf保护关闭
        //  xxx/send? name=fox?id=100   refer  token
    }


    @Autowired
    public DataSource dataSource;

    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        //设置数据源
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}

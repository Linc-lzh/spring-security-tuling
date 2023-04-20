package com.luban.fox.security01.config;

import com.luban.fox.security01.hander.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true,securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //auth.userDetailsService(userServiceImpl);
        auth.inMemoryAuthentication()
                .withUser("fox")
                .password(passwordEncoder().encode("123456"))
                .authorities("admin","user","ROLE_admin");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin();

        // 授权
        http.authorizeRequests()
                .antMatchers("/admin/demo").access("permitAll()")
//                //.antMatchers("/admin/index").access("hasAuthority('admin')")
//                .antMatchers("/admin/index").access("@mySecurityExpression.hasPermission(request,authentication)")
//                //.antMatchers("/**/*.jpg").permitAll()
//                //.regexMatchers(".+[.]jpg").permitAll()
//                //.mvcMatchers("admin/index").servletPath("/web").permitAll()
                .anyRequest().authenticated();// 都要认证

        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());


        http.csrf().disable();  // csrf保护关闭

    }



}

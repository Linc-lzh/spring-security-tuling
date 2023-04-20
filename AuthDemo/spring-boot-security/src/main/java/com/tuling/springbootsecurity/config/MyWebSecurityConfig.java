package com.tuling.springbootsecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.io.PrintWriter;

/**
 * 注入一个自定义的配置
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    //配置安全拦截策略
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //链式配置拦截策略
        http
                .csrf().disable()//关闭csrg跨域检查
                //这里注意matchere是有顺序的。
                .authorizeRequests()
                .antMatchers("/mobile/**").hasAuthority("mobile")
                .antMatchers("/salary/**").hasAuthority("salary")
                .antMatchers("/worker/**").hasAuthority("ROLE_worker")//等价于hasRole("worker")
                .antMatchers("/common/**").permitAll() //common下的请求直接通过
                .antMatchers("/**.html","/js/**","/css/**","/img/**").permitAll()//放行静态资源
                .anyRequest().authenticated() //其他请求需要登录
                .and()//并行条件
                .formLogin()
//                .successHandler((request,response,authentication)->{
//                    response.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = response.getWriter();
//                    out.write(authentication.getName());
//                    out.flush();
//                    out.close();
//                })
//                .loginPage("/index.html")//自定义登录页面
                .defaultSuccessUrl("/main.html").failureUrl("/common/loginFailed"); //可从默认的login页面登录，并且登录后跳转到main.html
    }

//创建默认用户的第二个方案
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService());
//        auth.inMemoryAuthentication()
//                .withUser(User.withUsername("admin").password("admin").authorities("mobile","salary").build())
//                .withUser(User.withUsername("manager").password("manager").authorities("salary").build())
//                .withUser(User.withUsername("worker").password("worker").authorities("worker").build());
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
}

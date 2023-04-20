package com.tuling.security.distributed.salary.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {

    public static final String RESOURCE_SALARY = "salary";

    //TODO 使用JWT令牌，需要引入与uaa一致的tokenStore，存储策略。
//    @Autowired
//    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_SALARY) //资源ID
                .tokenServices(tokenServices()) //使用远程服务验证令牌的服务
                //TODO 使用JWT令牌验证，就不需要调用远程服务了，用本地验证方式就可以了。
//                .tokenStore(tokenStore)
                .stateless(true);
    }

    //配置安全策略
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //校验请求
                .antMatchers("/order/**") // 路径匹配规则。
                .access("#oauth2.hasScope('all')") // 需要匹配scope
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    //配置access_token远程验证策略。
    //这里每次请求都会去远程服务器上验证，这会加大系统的负担。而后续的改进方法就是采用JWT令牌。
    //使用JWT令牌就不再需要远程验证服务，本地可以解析
    public ResourceServerTokenServices tokenServices(){
//        DefaultTokenServices services = new DefaultTokenServices();
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http://localhost:53020/uaa/oauth/check_token");
        //设置本客户端分配的client信息。
        services.setClientId("c1");
        services.setClientSecret("secret");
        return services;
    }
}

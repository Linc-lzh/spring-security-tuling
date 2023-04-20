package com.tuling.security.distributed.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenConfig {

    @Bean
    public TokenStore tokenStore(){
        //使用基于内存的普通令牌
        return new InMemoryTokenStore();
//        return new JdbcTokenStore(DataSource dataSource);
//        return new JwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer);
//        return new RedisTokenStore(RedisConnectionFactory connectionFactory);
    }

//    private static final String SIGN_KEY="uaa";
//
//    // TODO 使用JWT令牌。
//    @Bean
//    public TokenStore tokenStore(){
//        return new JwtTokenStore(accessTokenConvert());
//    }
//    @Bean
//    public JwtAccessTokenConverter accessTokenConvert(){
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(SIGN_KEY);
//        return converter;
//    }

}
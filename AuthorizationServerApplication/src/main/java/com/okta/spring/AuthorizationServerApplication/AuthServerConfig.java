package com.okta.spring.AuthorizationServerApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    @Value("${user.oauth.clientId}")
    private String ClientID;
    @Value("${user.oauth.clientSecret}")
    private String ClientSecret;
    @Value("${user.oauth.redirectUris}")
    private String RedirectURLs;

    public AuthServerConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(
        AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient(ClientID)
            .secret(passwordEncoder.encode(ClientSecret))
            .authorizedGrantTypes("authorization_code")
            .scopes("user_info")
//            .autoApprove(true)
            .redirectUris(RedirectURLs)
        .and()
        .inMemory()
        .withClient("ee0e0710193b7cac1e68")
        .secret(passwordEncoder.encode("7419ca6fa807e11d144823d8c0f614676cb35da7"))
        .authorizedGrantTypes("authorization_code")
        .scopes("user_info")
        .autoApprove(true)
        .redirectUris("http://localhost:8082/login/oauth2/code/")

        .and()
        .inMemory()
        .withClient("973886123")
        .secret(passwordEncoder.encode("3253f16e8324a73f6ede08c7405c0bad"))
        .authorizedGrantTypes("authorization_code")
        .scopes("user_info")
        .autoApprove(true)
        .redirectUris("http://localhost:8082/login/oauth2/code/")
        ;
    }
}

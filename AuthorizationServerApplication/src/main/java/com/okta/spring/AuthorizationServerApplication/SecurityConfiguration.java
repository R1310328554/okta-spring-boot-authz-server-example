
package com.okta.spring.AuthorizationServerApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${user.oauth.user.username}")
    private String username;
    @Value("${user.oauth.user.password}")
    private String password;

    @Autowired
    private SuccessAuthentication successAuthentication;
    @Autowired
    private FailureAuthentication failureAuthentication;
    @Autowired
    private UnauthorizedEntryPoint unauthorizedEntryPoint;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/css/**", "/templates/**", "/static/**", "/images/**");
    }

    //  Full authentication is required to access this resource
    protected void configure12(HttpSecurity http) throws Exception {
        http.requestMatchers()

                // 为什么需要放行 /login、/oauth/authorize
                .antMatchers("/login", "/oauth/authorize")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
//        http
////                .headers().frameOptions().disable()
////                .and()
//                .csrf().disable()
//                .requestMatchers()
//                    .antMatchers("/myLogin","/doLogin", "/oauth/authorize")// 这里antMatchers必须要包括/login2， 否则永远都是登录页面
//                .and()
//                    .authorizeRequests()
//                    .antMatchers("/aa","/myLogin", "/doLogin","/user/me2", "/oauth/authorize") // "/user/me" 放行之后， 不能获取到Principal参数
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated()
//                .and()
//                    .formLogin()
//                    .loginPage("/myLogin")
//                    .loginProcessingUrl("/doLogin").permitAll()
//                .anyRequest().authenticated()
        ;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()// 它的作用是什么？ 仅仅是一个通知作用吧..不对！ 测试发现，只有配置了loginPage，那么就一定需要配置loginProcessingUrl， 而且需要匹配！
                .requestMatchers()
                .antMatchers("/myLogin","/doLogin", "/oauth/authorize")// 这里antMatchers必须要包括/doLogin， 否则永远都是登录页面
                .and()
                .authorizeRequests()
                .antMatchers("/aa","/myLogin", "/doLogin","/user/me2", "/oauth/authorize") //这里如果 "/user/me" 放行，则不能获取到Principal参数
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/myLogin")
                .loginProcessingUrl("/doLogin").permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated() // 不能加这行，
//                否则：一直401 <oauth><error_description>Full authentication is required to access this resource</error_description><error>unauthorized</error></oauth>
        .and()
        .logout()
        .logoutUrl("myLogout")//默认logout 页面， 不是登出处理路径，其实也没有什么处理路径；没有logoutProcessingUrl

        // defaultTarget must start with '/' or with 'http(s)'
        .logoutSuccessUrl("/myLogoutSuccessUrl")
        // .logoutSuccessHandler(tigerLogoutSuccessHandler)  //url和Handler只能配置一个
//        .deleteCookies("JSESSIONID")//清除cook键值
        ;

    }

    protected void configure2(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().successHandler(successAuthentication).failureHandler(failureAuthentication);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser(username)
            .password(passwordEncoder.encode(password))
            .roles("USER")
        .and()
        .withUser("b")
                .password(passwordEncoder.encode("2"))
                .roles("USER")
        ;
    }

}

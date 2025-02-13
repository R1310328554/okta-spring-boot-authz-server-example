package com.lk.learn;

import com.lk.learn.config.TigerLogoutSuccessHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@SpringBootApplication
public class Ssecurity001Application {

	public static void main(String[] args) {
		SpringApplication.run(Ssecurity001Application.class, args);
	}


	//退出
	@Bean
	@ConditionalOnMissingBean(LogoutSuccessHandler.class)
	public LogoutSuccessHandler tigerLogoutSuccessHandler(){
		return new TigerLogoutSuccessHandler( );
	}

	// token 存储到数据库 https://blog.csdn.net/qq_37142346/article/details/80114609
//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
////		tokenRepository.getTokenForSeries(""); // 设置数据源
////        tokenRepository.setCreateTableOnStartup(true); // 启动创建表，创建成功后注释掉
//		return tokenRepository;
//	}


	@Bean
	CorsConfigurationSource myCorsConfigurationSource(){
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		//允许从百度站点跨域
		corsConfiguration.setAllowedOrigins(Arrays.asList("https://www.baidu.com"));
		//允许GET和POST方法
		corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST"));
		//允许携带凭证
		corsConfiguration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		//对所有URL生效
		source.registerCorsConfiguration("/**",corsConfiguration);
		return source;
	}

}

package br.com.ultracar.treinamento.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

/**
 * @author Thiago LS
 *
 * */
@Configuration
@EnableWebSecurity
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/usuarios/**").permitAll()
				.antMatchers("/grupo-acesso/**").permitAll()
				.antMatchers("/permissao-acesso/**").permitAll()
				.antMatchers("/operacoes/**").permitAll()
				.antMatchers("/menus/**").permitAll()
				.antMatchers("/api/cidades/**").permitAll()
				.antMatchers("/api/estados/**").permitAll()
				.antMatchers("/api/bairros/**").permitAll()
				.antMatchers("/api/enderecos/**").permitAll()
				.antMatchers("/api/complementos/**").permitAll()
				.antMatchers("/api/ceps/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public MethodSecurityExpressionHandler createExpressionHandler() {
		return new OAuth2MethodSecurityExpressionHandler();
	}
	
}

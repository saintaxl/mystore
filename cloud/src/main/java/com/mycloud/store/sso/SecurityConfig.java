/**
 * 
 */
package com.mycloud.store.sso;

import java.util.Collections;
import java.util.LinkedHashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.DelegatingLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;

/**
 * @author Shawn
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
    private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
		// auth.authenticationProvider(daoAuthenticationProvider());
	}

	//@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
		impl.setUserDetailsService(userDetailsService);
		impl.setPasswordEncoder(passwordencoder());
		impl.setHideUserNotFoundExceptions(false);
		return impl;
	}

	@Value("#{'${whitelist}'.split(',')}")
	private String[] whichlist;

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() // 1
		        .antMatchers(whichlist).permitAll() // 2
		        .antMatchers("/admin/**").hasRole("ADMIN") // 3
		        .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')").anyRequest().authenticated()
		        .and().formLogin().usernameParameter("email").passwordParameter("password")
		        .loginPage("/logon.htm")
		        .loginProcessingUrl("/logon")
		        .failureForwardUrl("/logon.htm")
		        .defaultSuccessUrl("/homepage.htm")
		        // .successHandler(successHandler)
		        .and().rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(1209600)
		        .and().csrf()
		        .and()
		        .httpBasic().and();

		http.logout() // 1
		        .logoutUrl("/logout")// 2
		        // .logoutSuccessUrl("/successLogot.htm") // 3
		        .logoutSuccessHandler(logoutSuccessHandler())// 4
		        .invalidateHttpSession(true) // 5
		        // .addLogoutHandler(logoutHandler) // 6
		        // .deleteCookies(cookieNamesToClear) //7
		        .and();
	}
	
	@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }

	@Bean
	public DelegatingLogoutSuccessHandler logoutSuccessHandler() {
		LinkedHashMap<RequestMatcher, LogoutSuccessHandler> matcherToHandler = new LinkedHashMap<RequestMatcher, LogoutSuccessHandler>();
		matcherToHandler.put(new MediaTypeRequestMatcher(new ParameterContentNegotiationStrategy(Collections.singletonMap("html", MediaType.TEXT_HTML)), MediaType.TEXT_HTML), new HttpStatusReturningLogoutSuccessHandler(HttpStatus.FOUND));
		DelegatingLogoutSuccessHandler delegatingLogoutSuccessHandler = new DelegatingLogoutSuccessHandler(matcherToHandler);
		SimpleUrlLogoutSuccessHandler simpleUrlLogoutSuccessHandler = new SimpleUrlLogoutSuccessHandler();
		simpleUrlLogoutSuccessHandler.setDefaultTargetUrl("/index.htm");
		delegatingLogoutSuccessHandler.setDefaultLogoutSuccessHandler(simpleUrlLogoutSuccessHandler);
		return delegatingLogoutSuccessHandler;
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
}

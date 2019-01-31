package com.petclinic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**", "/", "/index").permitAll()
								.antMatchers("/member/**").hasRole("USER")
								.antMatchers("/h2-console/**").permitAll()
								.and()
								.formLogin().loginPage("/login").failureUrl("/login-error");
		//For H2 db console
		http.csrf().disable();
		http.headers().frameOptions().disable();		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("John")
			.password("{noop}1234")
			.roles("USER");
									
	}
	
}

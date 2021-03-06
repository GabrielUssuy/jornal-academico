package br.edu.utfpr.gerenciadorweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private  CustomFailureLoginHandler loginFailureHandler;


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
			.antMatchers("/css/**", "/fonts/**", "/img/**", "/js/**", "/login/**", "/componentes/**")
			.permitAll().anyRequest().authenticated()
			.and().formLogin()
				.failureUrl("/login?error")
				.loginPage("/login")
					.successHandler(successHandler())
					.failureHandler(loginFailureHandler)
					.usernameParameter("login")
					.passwordParameter("password")
					.permitAll().and()
					.logout().logoutUrl("/logout")
						.logoutSuccessHandler(new CustomLogoutSuccessHandler())
						.deleteCookies("JSESSIONID")
						.and()
						.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(this.service).passwordEncoder(passwordEncoder());
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}
	
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
	    return new SuccessHandler();
	}
	

}
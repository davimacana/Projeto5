package com.eventos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eventos.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Configurações de acesso Spring Security
	 * @param HttpSecurity
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/", "/home").authenticated().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/dashboard", true).permitAll() 
				.and()
				.logout().logoutSuccessUrl("/").logoutUrl("/logout").permitAll();

		http.exceptionHandling().accessDeniedPage("/acessoNegado");

		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
package com.eventos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eventos.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final static String[] PERMIT_PATH =  {"/resources/**", "/css/**","/fonts/**","/img/**","/js/**","/favicon.png","/esqueci-minha-senha","/cadastrar"};
	
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
				.antMatchers(PERMIT_PATH).permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/dashboard", true).permitAll().and().logout().logoutUrl("/logout").permitAll();

		http.exceptionHandling().accessDeniedPage("/acessoNegado");
		
		http.sessionManagement().maximumSessions(1)
			.expiredUrl("/login?logout").maxSessionsPreventsLogin(false)
			.sessionRegistry(sessionRegistry());
		
		http.csrf().disable();
		
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        
		auth.userDetailsService(usuarioService).passwordEncoder(new BCryptPasswordEncoder());
    }
	
	@Bean(name = "sessionRegistry")
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
package searchAPI.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**","/console/*").permitAll().and()
		        .formLogin().usernameParameter("userId").passwordParameter("password").loginPage("/logIn").loginProcessingUrl("/signIn").defaultSuccessUrl("/").permitAll().and()
				.logout().logoutUrl("/logout")
				.logoutSuccessUrl("/logIn").invalidateHttpSession(true).permitAll().and()
				.csrf().disable()
				.headers().frameOptions().disable();
	}
	
}

package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//OLD WAY - DEPRECATED

//@EnableWebSecurity
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.csrf().disable()
//			.authorizeRequests()
//			.antMatchers("/").permitAll()
//			.antMatchers("/user").hasRole("USER")
//			.antMatchers("/admin").hasRole("ADMIN")
//			//.anyRequest().authenticated()
//			.and().httpBasic();
//	}
//@Override
//public void configure(WebSecurity web) throws Exception {
//    web.ignoring().antMatchers("/");
//}
//
//}

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		
		UserDetails user = User.withDefaultPasswordEncoder()
							.username("user")
							.password("password")
							.roles("USER")
							.build();
		
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("password")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	@Bean
	public SecurityFilterChain seecurityFilterChain(HttpSecurity http) throws Exception {
		
		//Without Lambda DSR
//		return http
//			.csrf().disable()
//			.authorizeRequests()
//			.antMatchers("/").permitAll()
//			.antMatchers("/user").hasRole("USER")
//			.antMatchers("/admin").hasRole("ADMIN")
//			.anyRequest().authenticated()
//			.and().httpBasic()
//			.and().build();
		
		return http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> {
					auth.antMatchers("/").permitAll();
					auth.antMatchers("/user").hasRole("USER");
					auth.antMatchers("/administrator").hasRole("ADMIN");
					// auth.anyRequest().authenticated();
				})
				.httpBasic(withDefaults())
				.build();
	}
	

		

	
}
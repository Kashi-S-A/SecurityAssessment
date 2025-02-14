package com.ks.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ks.jwt.AppFilter;
import com.ks.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppFilter appFilter;

	@Bean
	public PasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder(pwdEncoder());
		
		return provider;
	}
	

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return  http
            .csrf().disable()
            .authorizeRequests()
            .requestMatchers("/h2-console","/auth/**")
            .permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/manager/**").hasRole("MANAGER")
            .requestMatchers("/user/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .sessionManagement()
		    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    .and()
		    .authenticationProvider(authProvider())
		    .addFilterBefore(appFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

   

//    @Bean
//    public UserDetailsService userDetailsService() {
//        // This is for in-memory authentication; for DB-based, you would create a custom UserDetailsService.
//        return new InMemoryUserDetailsManager(
//            User.withUsername("admin")
//                .password(passwordEncoder().encode("admin@123"))
//                .roles("ADMIN")
//                .build(),
//            User.withUsername("manager")
//                .password(passwordEncoder().encode("manager"))
//                .roles("MANAGER")
//                .build(),
//            User.withUsername("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER")
//                .build()
//        );
//    }
}

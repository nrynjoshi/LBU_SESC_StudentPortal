package com.narayanjoshi.lbu.sesc.studentportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@ComponentScan("com.narayanjoshi.lbu")
public class SecurityConfig {

    @Autowired
    private CustomPortalAuthenticationProvider portalAuthProvider;

    @Bean
    public AuthenticationManager portalAuthManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(portalAuthProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	 http.csrf()
         .disable()

         .formLogin()
         .loginPage("/login")
         .loginProcessingUrl("/login")
         .defaultSuccessUrl("/dashboard", true)
         .failureUrl("/login?error=true")
         .and()
         .logout()
         .logoutUrl("/logout")
         .deleteCookies("JSESSIONID")
         .and()
         
         .authorizeRequests()
         .antMatchers("/api/**")
         .hasRole("API")
         .antMatchers("/login*")
         .permitAll()
         .antMatchers("/register*")
         .permitAll()
         .anyRequest()
         .authenticated();
    	 
        return http.build();
    }

}
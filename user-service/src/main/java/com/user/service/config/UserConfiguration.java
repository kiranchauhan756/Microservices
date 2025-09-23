package com.user.service.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserConfiguration {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails john= User.builder().username("Kiran").password("{noop}123").roles("USER").build();
        UserDetails mary= User.builder().username("mary").password("{noop}456").roles("USER","MANAGER","ADMIN").build();
        UserDetails suzi= User.builder().username("suzi").password("{noop}789").roles("USER","MANAGER","ADMIN").build();
        return new InMemoryUserDetailsManager(john,mary,suzi);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer.requestMatchers(HttpMethod.GET, "/user").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/user/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/user").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/user/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
        );
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }
}

package com.user.service.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class UserConfiguration {

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//        UserDetails john= User.builder().username("john").password("{noop}123").roles("USER").build();
//        UserDetails mary= User.builder().username("mary").password("{noop}123").roles("USER","MANAGER","ADMIN").build();
//        UserDetails suzi= User.builder().username("suzi").password("{noop}123").roles("USER","MANAGER","ADMIN").build();
//        return new InMemoryUserDetailsManager(john,mary,suzi);
//    }

//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=?");
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
//        return jdbcUserDetailsManager;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests(configurer ->
//                configurer.requestMatchers(HttpMethod.GET, "/user").hasRole("USER")
//                        .requestMatchers(HttpMethod.GET, "/user/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/user").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.PUT,  "/user/**").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.PATCH,"/user/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
//        );
//        httpSecurity.httpBasic(Customizer.withDefaults());
//        httpSecurity.csrf(csrf -> csrf.disable());
//        return httpSecurity.build();
//    }
}

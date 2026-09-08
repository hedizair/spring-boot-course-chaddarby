package com.hzair.springboot.cruddemo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.hzair.springboot.cruddemo.CruddemoApplication;

@Configuration
public class DemoSecurityConfig {

    private final CruddemoApplication cruddemoApplication;

    DemoSecurityConfig(CruddemoApplication cruddemoApplication) {
        this.cruddemoApplication = cruddemoApplication;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() { //* Create In Memory user with their roles and password

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123") // * {noop} is used to indicate that the password is stored in plain text and no encoding is applied.
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // * Configure each roles authorization for each HTTP method and endpoint
        http.authorizeHttpRequests(configurer -> 
            configurer
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // * Use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // * Disable Cross Site Request Forgery (CSRF) for Postman testing (not required for stateless REST APIs in general)
        http.csrf(csrf -> csrf.disable());

        return http.build();
    } 
}

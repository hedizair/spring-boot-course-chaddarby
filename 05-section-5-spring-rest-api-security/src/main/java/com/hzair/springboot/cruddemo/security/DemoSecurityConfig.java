package com.hzair.springboot.cruddemo.security;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.hzair.springboot.cruddemo.CruddemoApplication;

@Configuration
public class DemoSecurityConfig {

    private final CruddemoApplication cruddemoApplication;

    DemoSecurityConfig(CruddemoApplication cruddemoApplication) {
        this.cruddemoApplication = cruddemoApplication;
    }


    // * Add support for JDBC, no more hardcoded users in memory.
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) { // * DataSource auto injected by spring boot

        // * If custom tables are provided, here is the code to tell Spring Security to use them.
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // * Define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_id, pw, active from members WHERE user_id = ?");

        // * Define a qyery to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id, role from roles WHERE user_id = ?");

        return jdbcUserDetailsManager;
        // ------------------------------------------

        // * If no custom tables are provided, Spring Security will use the default tables (users & authorities), no need to do anything.
        // return new JdbcUserDetailsManager(dataSource); // * Tell Spring Security to use JDBC authentication with the provided DataSource
    }

    // @Bean
    // public InMemoryUserDetailsManager userDetailsManager() { //* Create In Memory user with their roles and password

    //     UserDetails john = User.builder()
    //             .username("john")
    //             .password("{noop}test123") // * {noop} is used to indicate that the password is stored in plain text and no encoding is applied.
    //             .roles("EMPLOYEE")
    //             .build();

    //     UserDetails mary = User.builder()
    //             .username("mary")
    //             .password("{noop}test123")
    //             .roles("EMPLOYEE", "MANAGER")
    //             .build();

    //     UserDetails susan = User.builder()
    //             .username("susan")
    //             .password("{noop}test123")
    //             .roles("EMPLOYEE", "MANAGER", "ADMIN")
    //             .build();

    //     return new InMemoryUserDetailsManager(john, mary, susan);
    // }

    @Bean 
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // * Configure each roles authorization for each HTTP method and endpoint
        http.authorizeHttpRequests(configurer -> 
            configurer
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasRole("MANAGER")
        );

        // * Use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // * Disable Cross Site Request Forgery (CSRF) for Postman testing (not required for stateless REST APIs in general)
        http.csrf(csrf -> csrf.disable());

        return http.build();
    } 
}

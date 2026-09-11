package com.hzair.springboot.mvcsecurity.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    // @Bean
    // public InMemoryUserDetailsManager userDetailsManager() {

    // UserDetails john = User.builder()
    // .username("john")
    // .password("{noop}test123")
    // .roles("EMPLOYEE")
    // .build();

    // UserDetails mary = User.builder()
    // .username("mary")
    // .password("{noop}test123")
    // .roles("EMPLOYEE", "MANAGER")
    // .build();

    // UserDetails susan = User.builder()
    // .username("susan")
    // .password("{noop}test123")
    // .roles("EMPLOYEE", "MANAGER", "ADMIN")
    // .build();

    // return new InMemoryUserDetailsManager(john, mary, susan);
    // }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) { // * Auto inject by spring */

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // ! Tell Spring Security how to find the users with a query
        userDetailsManager.setUsersByUsernameQuery("SELECT user_id, pw, active FROM members WHERE user_id=?");

        // ! Tell Spring Security how to find the roles with a query
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id=?");

        return userDetailsManager; // ! Telling spring to use jdbc data source for our authentication
    }

    // ! Each request will pass in this method to do every check present on the
    // function.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // ! Configure login page path to avoid the default page of srping security

        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/").hasRole("EMPLOYEE") // ! restrict access using the users roles
                .requestMatchers("/leaders/**").hasRole("MANAGER")
                .requestMatchers("/systems/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()) // * Any request must be logged in
                .formLogin(form -> form
                        .loginPage("/showMyLoginPage") // * request to the login page (GetMapping("/showMyLoginPage"))

                        .loginProcessingUrl("/authenticate") /*
                                                              * request to the authentication process,
                                                              * no need to make a controller, it's free from Spring
                                                              * Security
                                                              * we can put whatever url, but it must be used on the html
                                                              * form actions !!
                                                              */
                        .permitAll() // * all user can access the login page
                )
                .logout(logout -> logout.permitAll() // * all user can logut
                )
                .exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied") // * url to the acess
                                                                                               // denied request
                );

        return http.build();
    }

}

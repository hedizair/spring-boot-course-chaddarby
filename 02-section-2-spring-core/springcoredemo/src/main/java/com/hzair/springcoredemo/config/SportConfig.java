package com.hzair.springcoredemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hzair.springcoredemo.common.Coach;
import com.hzair.springcoredemo.common.SwimCoach;

@Configuration // * This annotation is used to indicate that the class is a configuration class, which can contain bean definitions and other configuration settings for the Spring application context.
public class SportConfig {

    @Bean("aquatic") // * This annotation is used to indicate that the method returns a bean that should be managed by the Spring container. 
                     // * The name of the bean is specified as "aquatic". By default, the name of the bean is the same as the method name (swimCoach here).
    public Coach swimCoach() {
        return new SwimCoach();
    }
}

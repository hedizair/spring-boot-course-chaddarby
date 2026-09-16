package com.luv2code.springboot.thymeleafdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect 
@Component 
public class LoggingAspect {
    
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))") // ! All the controllers in the controller package
    private void forControllerPackage() {

    }

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage() {
        
    }

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage() {
        
    }
    
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
        
    }

    @Before("forAppFlow()")
    private void before(JoinPoint joinPoint) {
        
        logger.info("===> in @Before | method called: "+ joinPoint.toShortString());

        for (Object arg : joinPoint.getArgs()) {
            logger.info("=== ===>arg: "+arg);
        }
    }

    @AfterReturning(pointcut="forAppFlow()",returning="result")
    private void afterReturning(JoinPoint joinPoint, Object result) {
        
        logger.info("===> in @AfterReturning | method called: "+ joinPoint.toShortString());

        logger.info("=== ===>" + result);
    }
}

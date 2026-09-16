package com.hzair.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component // * So Spring can use the component scanning to find this component */
@Order(100)
public class ApiAnalyticsAspect {

    
    @Before("com.hzair.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("=====>>> Performing @Before API analytics...");
    }

}

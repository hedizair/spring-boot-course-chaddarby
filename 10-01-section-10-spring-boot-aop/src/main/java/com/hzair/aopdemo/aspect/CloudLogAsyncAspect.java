package com.hzair.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component // * So Spring can use the component scanning to find this component */
@Order(1)
public class CloudLogAsyncAspect {


    @Before("com.hzair.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void logToCloudAsync() {
        System.out.println("=====>>> Logging @Before to Cloud in async fasion...");
    }

}

package com.hzair.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect // * @Aspect is not required if you add advices in this class (@Before @AfterReturnin...)
@Component // * So Spring can use the component scanning to find this component */
public class AopExpressions {
    // ! We can declare a poincut expression reusable throughout the Component
    @Pointcut("execution(* com.hzair.aopdemo.dao.*.*(..))") // ! (...) Any method of any Class inside the com.hzair.aopdemo.dao package
    public void forDaoPackage() {}

    @Pointcut("execution(* com.hzair.aopdemo.dao.*.get*(..))")
    public void getter() {}

    @Pointcut("execution(* com.hzair.aopdemo.dao.*.set*(..))")
    public void setter() {}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() { }
}

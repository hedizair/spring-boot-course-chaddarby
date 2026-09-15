package com.hzair.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component //* So Spring can use the component scanning to find this component */
public class LoggingAspect {
    

    // ! We can declare a poincut expression reusable throughout the Component
    @Pointcut("execution(* com.hzair.aopdemo.dao.*.*(..))") // ! (...) Any method of any Class inside the com.hzair.aopdemo.dao package 
    private void forDaoPackage() {}

    // * Here is the Pattern to make pointcut expression to point more precisely functions : 
    // * execution(modifiers-pattern?(optional) return-type-pattern declaring-type-pattern?(optional) method-name-pattern(param-pattern) throws-pattern?(optional)) 
    
    // * For exemple : @Before ("execution(public void addAccount())") | Modifier = public ; void = Return type; addAcount() = Methode

    // @Before ("execution(public void addAccount())") // ! beforeAddAccountAdvice() will be exectuted before each public, returning void, named addAccount() call throughout the app

    // @Before ("execution(public void com.hzair.aopdemo.dao.AccountDAO.addAccount())") // ! (...)  before each public method, returning void throughout the void com.hzair.aopdemo.dao.AccountDAO class

    // @Before ("execution(public void add*())") // ! (...)  public method starting with "add", returning void throughout the app

    // @Before ("execution(* add*())") // ! (...)  method returning any type, AND starting with "add" throughout the app

    // @Before ("execution(* add*(com.hzair.aopdemo.Account))") // ! (...) method returning any type, AND starting with "add", AND have only one parameter of type Account throughout the app

    // @Before ("execution(* add*(com.hzair.aopdemo.Account, ..))") // ! (...) method returning any type, AND starting with "add", AND has atleast (au moins) one parameter of type Account throughout the app
    
    // @Before ("execution(* add*(..))") // ! (...) all method starting with "add" throughout the app

    // @Before ("execution(* com.hzair.aopdemo.dao.*.*(..))") // ! (...) Any method of any Class inside the com.hzair.aopdemo.dao package

    @Before ("forDaoPackage()") // ! (...) Any method of any Class inside the com.hzair.aopdemo.dao package
    public void beforeAddAccountAdvice() {
        System.out.println("\n=====>>> Executing @Before advice on addAcount()...");
    }



    @Before ("forDaoPackage()") 
    public void performApiAnalytics() {
        System.out.println("\n=====>>> Performing API analytics...");
    }

}

package com.hzair.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.hzair.aopdemo.Account;

@Aspect
@Component // * So Spring can use the component scanning to find this component */
@Order(10)
public class LoggingAspect {

    // * Here is the Pattern to make pointcut expression to point more precisely functions :
    // * execution(modifiers-pattern?(optional) return-type-pattern declaring-type-pattern?(optional) method-name-pattern(param-pattern) throws-pattern?(optional))

    // * For exemple : @Before ("execution(public void addAccount())") | Modifier = public ; void = Return type; addAcount() = Methode

    // @Before ("execution(public void addAccount())") // ! beforeAddAccountAdvice() will be exectuted before each public, returning void, named addAccount() call throughout the app

    // @Before ("execution(public void com.hzair.aopdemo.dao.AccountDAO.addAccount())") // ! (...) before each public method, returning void throughout the void  com.hzair.aopdemo.dao.AccountDAO class

    // @Before ("execution(public void add*())") // ! (...) public method starting with "add", returning void throughout the app

    // @Before ("execution(* add*())") // ! (...) method returning any type, AND starting with "add" throughout the app

    // @Before ("execution(* add*(com.hzair.aopdemo.Account))") // ! (...) method returning any type, AND starting with "add", AND have only one parameter of type Account throughout the app

    // @Before ("execution(* add*(com.hzair.aopdemo.Account, ..))") // ! (...)  method returning any type, AND starting with "add", AND has atleast (au moins) one parameter of type Account throughout the app

    // @Before ("execution(* add*(..))") // ! (...) all method starting with "add" throughout the app

    // @Before ("execution(* com.hzair.aopdemo.dao.*.*(..))") // ! (...) Any method  of any Class inside the com.hzair.aopdemo.dao package

    // @Before ("forDaoPackage()") // ! (...) Any method of any Class inside the com.hzair.aopdemo.dao package

    @Before("com.hzair.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()") // ! to call a pointer expression provided by another class
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) { // ! JoinPoint has metadata about method call, like  method signature, parameters ...
        System.out.println("=====>>> Executing @Before advice on addAcount()...");

        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        Object[] args = theJoinPoint.getArgs();

        for (Object tempArg : args) {
            System.out.println("        " + tempArg);

            if (tempArg instanceof Account) {
                Account acc = (Account) tempArg;
                System.out.println("Account process: " + acc.getName() + " - " + acc.getLevel());
            }
        }
        System.out.println("\n");

    }

    @AfterReturning( // ! @AfterReturning mean just after the return statement of the function
            pointcut = "execution(* com.hzair.aopdemo.dao.AccountDAO.findAccounts(..))"
            , returning = "result") // ! variable name where the return value will be stocked (List<Account> result(HERE))
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result) {

        String method = joinPoint.getSignature().toShortString();

        System.out.println("=====>>> Executing @AfterReturning on method: " + method);
        System.out.println("Result is: " + result);

        convertAccountNamesToUpperCase(result); // * We can modify the return statement before it will return to the
                                                // user
        System.out.println("\n");

    }

    @AfterThrowing( // ! @AfterThrowing mean just after an exception is thrown in the function
            pointcut = "execution(* com.hzair.aopdemo.dao.AccountDAO.findAccounts(..))"
            , throwing = "theException") // !  variable name where the exception  will be stocked  (Exception theException(HERE))
    public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Exception theException) {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("=====>>> Executing @AfterThrowing on method: " + method);

        System.out.println("The exception is: " + theException);
        System.out.println("\n");

    }

    @After("execution(* com.hzair.aopdemo.dao.AccountDAO.findAccounts(..))") // ! @After mean always after a method, ALWAYS, even if there is an exception.
    public void afterFinallyFindAccountAdvice(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("=====>>> Executing @After (finally) on method: " + method);
        System.out.println("\n");

    }

    @Around("execution(* com.hzair.aopdemo.service.TrafficFortuneService.getFortune(..))") // ! @Around, allow to execute before and after the method call, we decide in aroundGetFortune where to execute de proceedingJoinPoint that is the method getFortune()
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("=====>>> Executing @Around on method: " + proceedingJoinPoint.toShortString());
        System.out.println("=====>>>Starting the timestamp...");
        System.out.println("=====>>>Execute the advising method (getfortune())...");


        long begin = System.nanoTime();

        Object result = null;
        
        try {
            result = proceedingJoinPoint.proceed(); // * Execute the method
        } catch (Exception e) {
            System.out.println("=====>>>An exception has been thrown in the @Around: " + e.getMessage());

            // result = "Major accident! But no worries, your private AOP helicopter is on the way!";

            throw e; // ! We can rethrow the exception

        }
            
    
        long end = System.nanoTime();

        System.out.println("=====>>>Duration: " + (end-begin)/1000.0 + " NANO seconds");

        return result;
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        for (Account acc : result) {
            String accNameUpper = acc.getName().toUpperCase();
            acc.setName(accNameUpper);
        }
    }

}

package com.hzair.aopdemo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hzair.aopdemo.dao.AccountDAO;
import com.hzair.aopdemo.dao.MemberShipDAO;
import com.hzair.aopdemo.service.TrafficFortuneService;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MemberShipDAO memberShipDAO,
			TrafficFortuneService trafficFortuneService) { // * Auto Inject
		return runner -> {

			// demoTheBeforeAdvice(accountDAO, memberShipDAO);
			// demoTheAfterReturningAdvice(accountDAO);
			// demoTheAfterThrowingAdvice(accountDAO);
			// demoTheAfterAdvice(accountDAO);
			// demoTheAroundAdvice(trafficFortuneService);
			// demoTheAroundAdviceHandleException(trafficFortuneService);
			demoTheAroundAdviceRehtrowExceptionException(trafficFortuneService);
		};
	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO, MemberShipDAO memberShipDAO) {

		Account acc = new Account("My Acount", "Level 3");

		accountDAO.addAccount(acc, true);

		System.out.println("\n");
		accountDAO.doWork();

		System.out.println("\n");
		accountDAO.getName();

		System.out.println("\n");
		accountDAO.setName("A Name");

		System.out.println("\n");
		accountDAO.getServiceCode();

		System.out.println("\n");
		accountDAO.setServiceCode("A Service code");

		System.out.println("\n");
		memberShipDAO.addSillyMember();

		System.out.println("\n");
		memberShipDAO.goToSleep();

	}

	private void demoTheAfterReturningAdvice(AccountDAO accountDAO) {

		List<Account> theAccounts = accountDAO.findAccounts();
		System.out.println("\n");
		System.out.println("Main program logs : demoTheAfterReturningAdvice");
		for (Account acc : theAccounts) {
			System.out.println("- " + acc);
		}
	}

	private void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {

		List<Account> theAccounts = null;
		try {
			theAccounts = accountDAO.findAccounts(true); // * Simulate an exception with tripWire = true
		} catch (Exception exc) {
			System.out.println("\n ");
			System.out.println("Main program logs : ... caught exception" + exc);

		}

		System.out.println(theAccounts);
	}

	private void demoTheAfterAdvice(AccountDAO accountDAO) {

		List<Account> theAccounts = null;
		try {
			theAccounts = accountDAO.findAccounts(false); // * Simulate an exception with tripWire = true
		} catch (Exception exc) {
			System.out.println("\n ");
			System.out.println("Main program logs : ... caught exception" + exc);

		}

		System.out.println(theAccounts);
	}

	private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {

		System.out.println("\nMain programm: demoTheAroundAdvice");
		System.out.println("Calling getFortune()");

		String data = trafficFortuneService.getFortune();

		System.out.println("The data: "+data);
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {

		System.out.println("\nMain programm: demoTheAroundAdviceHandleException");
		System.out.println("Calling getFortune()");

		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);

		System.out.println("The data: "+data);
	}


	private void demoTheAroundAdviceRehtrowExceptionException(TrafficFortuneService trafficFortuneService) {

		System.out.println("\nMain programm: demoTheAroundAdviceRehtrowExceptionHandleException");
		System.out.println("Calling getFortune()");

		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);

		System.out.println("The data: "+data);
	}

}

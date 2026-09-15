package com.hzair.aopdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hzair.aopdemo.dao.AccountDAO;
import com.hzair.aopdemo.dao.MemberShipDAO;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MemberShipDAO memberShipDAO) { // * Spirng auto
																										// inject
																										// because of
																										// @Bean here
																										// and
																										// @Repository
																										// on DAOImpl
		return runner -> {

			demoTheBeforeAdvice(accountDAO, memberShipDAO);
		};
	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO, MemberShipDAO memberShipDAO) {

		Account acc = new Account("My Acount", "Level 3");

		accountDAO.addAccount(acc, true);
		System.out.println("\n");
		memberShipDAO.addSillyMember();
		System.out.println("\n");
		accountDAO.doWork();
		System.out.println("\n");
		memberShipDAO.goToSleep();



	}

}

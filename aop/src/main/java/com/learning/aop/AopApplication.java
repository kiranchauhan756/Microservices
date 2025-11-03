package com.learning.aop;

import com.learning.aop.dao.AccountDAO;
import com.learning.aop.dao.MembershipDAO;
import com.learning.aop.entity.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {

		SpringApplication.run(AopApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO){
		return runner -> {
			accountDAO.addAccount(new Account(),true);
			membershipDAO.addMember();
			membershipDAO.updateMember();
			accountDAO.updateAccount();

		};
	}

}

package com.example.demo;

import com.example.demo.usedClasse.User;
import com.example.demo.usedClasse.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
      SpringApplication.run(DemoApplication.class,args);

	}
	@Bean
	public CommandLineRunner commandLineRunner(UserRepo userRepo){
		return runner->{
			 //createUser(userRepo);
			//readUser(userRepo);
			findAllUser(userRepo);
			//findByName(userRepo);
			//updateUser(userRepo);
			//deleteUser(userRepo);
		};
	}

	private void deleteUser(UserRepo userRepo) {
		System.out.println("Deleting the user");
		userRepo.deleteUser();
	}

	private void updateUser(UserRepo userRepo) {
		System.out.println("Finding the user");
		User user=userRepo.findById(4);
		System.out.println("Setting userName to Kiran");
		user.setName("Kiran");
		userRepo.updateUser(user);
		System.out.println("Retrieving the user....        "+user.getId()+" "+user.getName());

	}

	private void findByName(UserRepo userRepo) {
		List<User> user=userRepo.findByName("kittu");
		for(User u : user){
			System.out.println(u.getId()+" "+u.getName());
		}
	}

	private void findAllUser(UserRepo userRepo) {
		System.out.println("Creating Users....");
		User user1=new User("laxmi");
		User user2=new User("pooja");
		User user3=new User("rahul");
		User user4=new User("kittu");
		System.out.println("Saving Users...");
		userRepo.save(user1);
		userRepo.save(user2);
		userRepo.save(user3);
		userRepo.save(user4);
		System.out.println("retrieving all the users in order by form...");
		List<User> userList=userRepo.findAllUser();
        for(User u : userList){
			System.out.println(u.getId()+" "+u.getName());
		}
	}


	private void readUser(UserRepo userRepo) {
		System.out.println("Creating an user....");
		User user=new User("kittu");
		System.out.println("Saving the user");
		userRepo.save(user);
		int  id= (int) user.getId();
		User findUser=userRepo.findById(id);
		System.out.println("Retreiving the user"+findUser);
	}

	private void createUser(UserRepo userRepo) {
		System.out.println("Creating an user....");
		User user=new User("kittu");
		System.out.println("Saving the user");
		userRepo.save(user);
		System.out.println("Id of saved user");
		System.out.println(user.getId());
	}

}

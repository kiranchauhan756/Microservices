package com.mappings;

import com.mappings.dao.InstructorDAO;
import com.mappings.entities.Instructor;
import com.mappings.entities.InstructorDetail;
import org.aspectj.apache.bcel.generic.Instruction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringMappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMappingsApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO) {
		return runner -> {
			createInstructor(instructorDAO);
		};
	}

	private void createInstructor(InstructorDAO instructorDAO) {
		Instructor instructor=new Instructor("kiran","chauhan","kiran@gmial.com");
		InstructorDetail instructorDetail=new InstructorDetail("www.mychannel.youtoube","coding");
		instructor.setInstructorDetail(instructorDetail);
		System.out.println("saving instructor");
		instructorDAO.save(instructor);
		System.out.println("saved");

	}

}

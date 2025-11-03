package com.mappings;

import com.mappings.dao.InstructorDAO;
import com.mappings.service.ServiceImpl;
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
	public CommandLineRunner commandLineRunner(ServiceImpl instructorService, InstructorDAO instructorDAO) {
		return runner ->
			//instructorService.findCoursesForInstructor(instructorDAO);
		    //instructorService.findInstructorByIdJoinFetch(instructorDAO);
             //instructorService.update(instructorDAO);
             //  instructorService.updateCourse(instructorDAO);
            // instructorService.deleteInstructor(instructorDAO);
		     // instructorService.deleteCourse(instructorDAO);
		//instructorService.createInstructorWithReviews_Students(instructorDAO);
		//instructorService.deleteReviewsByCourseId(instructorDAO);
         //instructorService.findStudentAndCoursesByStudentId(instructorDAO);
		 instructorService.deleteStudent(instructorDAO);
	}

}

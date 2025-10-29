package com.mappings.service;

import com.mappings.dao.InstructorDAO;
import com.mappings.entities.Course;
import com.mappings.entities.Instructor;
import com.mappings.entities.InstructorDetail;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ServiceImpl {

    @Transactional
    public void findCoursesForInstructor(InstructorDAO instructorDAO) {
        int theId = 1;
        log.info("Finding instructor with id: {}" , theId);
        Instructor tempInstructor = instructorDAO.findInstructorById(theId);
        log.info("tempInstructor: {}", tempInstructor.getFirst_name());
        log.info("the associated courses: {}", tempInstructor.getCourses());
        log.info("Done!");
        findInstructorDetailById(instructorDAO);
    }
    
    public void findInstructorDetailById(InstructorDAO instructorDAO) {
        int id=1;
        Instructor instructor=instructorDAO.findInstructorById(id);
        log.info(instructor.getFirst_name());
        log.info(String.valueOf(instructor.getInstructorDetail()));
    }

    public void createInstructorWithCourse(InstructorDAO instructorDAO) {
        Instructor instructor=new Instructor("kiran","chauhan","kiran@gmail.com");
        InstructorDetail instructorDetail=new InstructorDetail("www.kiran.youtube","coding");
        Course course1=new Course("economics");
        Course course2=new Course("physics");
        instructor.setInstructorDetail(instructorDetail);
        instructor.add(course1);
        instructor.add(course2);
        log.info("saving instructor");
        instructorDAO.save(instructor);
        log.info("saved");
    }

    public void deleteInstructorDetail(InstructorDAO instructorDAO) {
        int id=2;
        log.info("Deleting it");
        instructorDAO.deleteInstructorDetailById(id);
    }

    public void findInstructorDetail(InstructorDAO instructorDAO) {
        int id=2;
        InstructorDetail instructorDetail=instructorDAO.findInstructorDetailById(id);
        log.info("The instructorDetail with id "+ id+"is ="+instructorDetail);
        Instructor instructor=instructorDetail.getInstructor();
        log.info("The instructor with id "+ id+ "is"+instructor);
    }

    public void deleteInstructor(InstructorDAO instructorDAO) {
        int id=1;
        instructorDAO.deleteInstructor(id);
    }

    public void findInstructor(InstructorDAO instructorDAO) {
        int id=1;
        Instructor instructor=instructorDAO.findInstructorById(id);
        log.info("The instructor with id "+ id+"is ="+instructor);
        InstructorDetail instructorDetail=instructor.getInstructorDetail();
        log.info("The instructor detail with id "+ id+ "is"+instructorDetail);
    }

    public void createInstructor(InstructorDAO instructorDAO) {
        Instructor instructor=new Instructor("pratibha","yadav","pratibha@gmail.com");
        InstructorDetail instructorDetail=new InstructorDetail("www.pratibha.youtube","cheating");
        instructor.setInstructorDetail(instructorDetail);
        log.info("saving instructor");
        instructorDAO.save(instructor);
        log.info("saved");

    }
}

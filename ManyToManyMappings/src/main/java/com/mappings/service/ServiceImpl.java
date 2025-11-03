package com.mappings.service;

import com.mappings.dao.InstructorDAO;
import com.mappings.entities.Course;
import com.mappings.entities.Instructor;
import com.mappings.entities.InstructorDetail;
import com.mappings.entities.Reviews;
import com.mappings.entities.Student;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
public class ServiceImpl {

    public void findCoursesForInstructor(InstructorDAO instructorDAO) {
        int theId = 1;
        log.info("Finding instructor with id: {}" , theId);
        Instructor tempInstructor = instructorDAO.findInstructorById(theId);
        log.info("tempInstructor: {}", tempInstructor.getFirst_name());
        List<Course> courses = tempInstructor.getCourses();
        log.info("the associated courses: {}", courses);
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
        Instructor instructor=instructorDAO.findInstructorById(1);
        List<Course> courses =instructor.getCourses();
        for(Course course: courses){
            course.setInstructor(null);
        }
        instructorDAO.deleteInstructor(id);
    }

    public void findInstructor(InstructorDAO instructorDAO) {
        int id=1;
        Instructor instructor=instructorDAO.findInstructorById(id);
        log.info("The instructor with id "+ id+"is ="+instructor);
        InstructorDetail instructorDetail=instructor.getInstructorDetail();
        log.info("The instructor detail with id {}is{}", id, instructorDetail);
    }

    public void createInstructor(InstructorDAO instructorDAO) {
        Instructor instructor=new Instructor("pratibha","yadav","pratibha@gmail.com");
        InstructorDetail instructorDetail=new InstructorDetail("www.pratibha.youtube","cheating");
        instructor.setInstructorDetail(instructorDetail);
        log.info("saving instructor");
        instructorDAO.save(instructor);
        log.info("saved");

    }

    public void findInstructorByIdJoinFetch(InstructorDAO instructorDAO) {
        int id=1;
        log.info("Finding instructor  with id {} ",id);
        Instructor tempInstructor=instructorDAO.findInstructorByIdJoinFetch(id);
        log.info("We found the instructor with id {} :{}",id,tempInstructor);
        log.info("Instructor with courses:{} ",tempInstructor.getCourses());
        log.info("Instructor Detail : {}",tempInstructor.getInstructorDetail());


    }

    public void update(InstructorDAO instructorDAO) {
        int id=1;
        log.info("Find instructor id:  {}",id);
        Instructor instructor=instructorDAO.findInstructorById(id);
        instructor.setLast_name("Developer");
        instructorDAO.update(instructor);

    }

    public void updateCourse(InstructorDAO instructorDAO) {
        int id=10;
        log.info("Finding course by id : {} ",id);
        Course course=instructorDAO.findCourseById(id);
        course.setTitle("english_tuition");
        instructorDAO.updateCourse(course);
    }

    public void deleteCourse(InstructorDAO instructorDAO) {
        int id=11;
        instructorDAO.deleteCourse(id);
    }

    public void createInstructorWithReviews_Students(InstructorDAO instructorDAO) {
        Instructor instructor=new Instructor("pratibha","yadav","pratibha@gmail.com");
        InstructorDetail instructorDetail=new InstructorDetail("www.pratibha.youtube","cheating");
        Course course1=new Course("economics");
        Course course2=new Course("physics");
        instructor.setInstructorDetail(instructorDetail);
        instructor.add(course1);
        instructor.add(course2);
        Reviews reviews1=new Reviews("I love the economics course");
        Reviews reviews11=new Reviews("Economics course is not well");
        Reviews reviews12=new Reviews("Not a detailed course");
        Reviews reviews13=new Reviews("best course");


        Reviews reviews2=new Reviews("I love the physics course");
        course1.addReviews(reviews1);
        course2.addReviews(reviews2);
        course1.addReviews(reviews11);
        course1.addReviews(reviews12);
        course1.addReviews(reviews13);
        log.info("Saving the instructor");

        Student student1=new Student("Kiran","Chauhan","kiran@gmail.com");
        Student student2=new Student("Pankaj","Tester","tester@gmail.com");
        course1.addStudent(student1);
        course1.addStudent(student2);
        course2.addStudent(student2);
        instructorDAO.save(instructor);
    }

    public void deleteReviewsByCourseId(InstructorDAO instructorDAO) {
        int id=10;
        instructorDAO.deleteReviewsByCourseId(id);
    }

    public void findStudentAndCoursesByStudentId(InstructorDAO instructorDAO) {
        int id=2;
        log.info("Find the student with id {} ",id);
        Student student=instructorDAO.findStudentAndCourseByStudentId(id);
        log.info(" This is the student with id {} {}",id,student);
        log.info("Find the courses with student id {} ",id);
        List<Course> courses=student.getCourses();
        log.info(" These are courses with student id :{}",id);
        log.info("{} ",courses);
    }

    public void deleteStudent(InstructorDAO instructorDAO) {
        int id=2;
        log.info("Deleting the student with id{} ",id);
        instructorDAO.deleteStudent(id);
        log.info("Deleted !");

    }
}

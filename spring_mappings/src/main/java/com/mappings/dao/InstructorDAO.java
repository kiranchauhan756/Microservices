package com.mappings.dao;

import com.mappings.entities.Course;
import com.mappings.entities.Instructor;
import com.mappings.entities.InstructorDetail;

public interface InstructorDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructor(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    Instructor findInstructorByIdJoinFetch(int id);
    void update (Instructor instructor);
    void updateCourse(Course course);
    Course findCourseById(int id);
    void deleteCourse(int id);
}

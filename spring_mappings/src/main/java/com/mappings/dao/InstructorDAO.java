package com.mappings.dao;

import com.mappings.entities.Instructor;
import com.mappings.entities.InstructorDetail;
import com.mappings.entities.Course;

import java.util.List;

public interface InstructorDAO {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructor(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    List<Course>  fetchCoursesByInstructor(int id);
    Instructor findInstructorByIdJoinFetch(int id);
}

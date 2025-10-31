package com.mappings.dao;

import com.mappings.entities.Course;
import com.mappings.entities.Instructor;
import com.mappings.entities.InstructorDetail;
import com.mappings.entities.Reviews;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstructorDAOImp implements InstructorDAO{

    private EntityManager entityManager;
    @Autowired
    public  InstructorDAOImp(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor instructor) {
          entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    public void deleteInstructor(int id) {
        Instructor instructor=findInstructorById(id);
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail=findInstructorDetailById(id);
        instructorDetail.getInstructor().setInstructorDetail(null);
         entityManager.remove(instructorDetail);
    }


    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
     TypedQuery<Instructor> query=entityManager.createQuery("select i from Instructor i "
                                                               +" JOIN FETCH i.courses"+
                                                                " JOIN FETCH i.instructorDetail"
                                                              + " where i.id=:id",Instructor.class);
     query.setParameter("id",id);
     return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
     entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
     entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class,id);
    }

    @Override
    public void deleteCourse(int id) {
        Course course=findCourseById(id);
        entityManager.remove(course);
    }


    @Override
    @Transactional
    public void deleteReviewsByCourseId(int id) {
        Course course=findCourseById(id);
        if(course!=null){
            course.getReviews().clear();
        }
    }


}

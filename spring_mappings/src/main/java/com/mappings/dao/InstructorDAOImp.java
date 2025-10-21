package com.mappings.dao;

import com.mappings.entities.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}

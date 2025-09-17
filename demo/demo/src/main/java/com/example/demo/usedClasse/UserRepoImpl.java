package com.example.demo.usedClasse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepoImpl implements  UserRepo{
    private final EntityManager entityManager;

    @Autowired
    public UserRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(Integer id) {
       return  entityManager.find(User.class,id);
    }

    @Override
    public List<User> findAllUser() {
        TypedQuery<User> theQuery=entityManager.createQuery("From User order by id",User.class);
        return theQuery.getResultList();

    }

    @Override
    public List<User> findByName(String theName) {
        TypedQuery<User> theQuery= entityManager.createQuery("From User where name=:theData",User.class);
        theQuery.setParameter("theData",theName);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteUser() {
        entityManager.createQuery("Delete from User").executeUpdate();
    }
}

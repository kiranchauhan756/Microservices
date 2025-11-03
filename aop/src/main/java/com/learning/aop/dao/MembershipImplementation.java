package com.learning.aop.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipImplementation implements MembershipDAO {

    @Override
    public boolean addMember() {
        System.out.println(getClass()+" Calling addMembership method");
        return true;
    }

    @Override
    public void updateMember() {
        System.out.println(getClass()+" Calling updateMembership method");

    }
}

package com.learning.aop.dao;

import com.learning.aop.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountImplementation implements AccountDAO {

    @Override
    public void addAccount(Account account,boolean addFlag) {
        System.out.println(getClass()+" Calling addAccount method");
    }

    @Override
    public void updateAccount() {
        System.out.println(getClass()+" Calling updateAccount method");

    }
}

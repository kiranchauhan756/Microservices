package com.learning.aop.dao;

import com.learning.aop.entity.Account;

public interface AccountDAO {
    void addAccount(Account account,boolean addFlag);
    void updateAccount();
}

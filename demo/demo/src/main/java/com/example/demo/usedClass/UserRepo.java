package com.example.demo.usedClass;

import java.util.List;

public interface UserRepo {
    void save(User user);
    User findById(Integer id);
    List<User> findAllUser();
    List<User> findByName(String theName);
    void updateUser(User user);
    void deleteUser();

}

package com.user.service.repository;

import com.user.service.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public User saveUser(User user) {
        users.add(user);
        return user;
    }

    public List<User> findAll() {
        return users;
    }
}

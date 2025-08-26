package com.user.service.repository;

import com.user.service.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public User saveUser(User user) {
        users.add(user);
        return user;
    }

    public List<User> findAllUsers() {
        return users;
    }

    public Optional<User> findById(Long id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }


    public void delete(User user) {
        users.remove(user);
    }

    public Optional<User> findByFirstName(String firstName) {
        return users.stream().filter(u -> u.getFirstName().equals(firstName)).findFirst();
    }

    public User updateUser(User user) {
        int index = users.indexOf(user);
        if (index != -1) {
            users.set(index, user);
        }
        return user;
    }
}

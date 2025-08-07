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

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(long id){
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }


    public void delete(User user) {
     users.remove(user);
    }

    public Optional<User> findByFirstName(String firstName) {
        return users.stream().filter(u->u.getFirstName().equals(firstName)).findFirst();
    }

    public User updateUser(long id){
        Optional<User> user=users.stream().filter(u-> u.getId()==id).findFirst();
        int index=users.indexOf(user.get());
        if(index!=-1){
            users.set(index,user.get());
        }
        return user.get();
    }
}

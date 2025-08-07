package com.user.service.service;

import com.user.service.converter.Converter;
import com.user.service.entity.User;
import com.user.service.entity.UserRequest;
import com.user.service.entity.UserResponse;
import com.user.service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final Converter converter;

    public UserService(UserRepository userRepo,Converter converter) {
        this.userRepo = userRepo;
        this.converter=converter;
    }

    public UserResponse save(UserRequest userRequest) {
        User user=(User) converter.convert(userRequest,new User());
        User savedUser=userRepo.saveUser(user);

        return (UserResponse) converter.convert(savedUser,new UserResponse());
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }
}

package com.user.service.service;

import com.user.service.converter.Converter;
import com.user.service.entity.User;
import com.user.service.entity.UserRequest;
import com.user.service.entity.UserResponse;
import com.user.service.exception.NoDataFoundException;
import com.user.service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User findById(long id) {
      return userRepo.findById(id).orElseThrow(()-> new NoDataFoundException("user with this id doesn't exist"));
    }

    public UserResponse updateUser(long id, UserRequest userRequest) {
        Optional<User> user=userRepo.findById(id);
        if(user.isPresent()){
            User user1=user.get();
            user1.setFirstName(userRequest.getFirstName());
            user1.setLastName(userRequest.getLastName());
            user1.setEmail(userRequest.getEmail());
            User savedUser=userRepo.updateUser(user1.getId());
            return (UserResponse) converter.convert(savedUser,new UserResponse());
        }else{
            throw new NoDataFoundException("user with this id doesn't exist");
        }


    }

    public void deleteUser(long id) {
        Optional<User> user=userRepo.findById(id);
        if(user.isPresent()){
            userRepo.delete(user.get());
        }
        else{
            throw new NoDataFoundException("user with this id doesn't exist");
        }
    }

    public User findByFirstName(String firstName) {
          return userRepo.findByFirstName(firstName).orElseThrow(()-> new NoDataFoundException("User with this firstName does not exist"));
    }

    public UserResponse updateUserPartially(long id, UserRequest userRequest) {
        Optional<User> user=userRepo.findById(id);
        if(user.isPresent()){
            User user1=user.get();
            if(userRequest.getFirstName()!=null)user1.setFirstName(userRequest.getFirstName());
            if(userRequest.getLastName()!=null)user1.setLastName(userRequest.getLastName());
            if(userRequest.getEmail()!=null)user1.setEmail(userRequest.getEmail());
            User savedUser=userRepo.updateUser(user1.getId());
            return (UserResponse) converter.convert(savedUser,new UserResponse());
        }
        else{
            throw  new NoDataFoundException("user with this id does not exist");
        }
    }
}

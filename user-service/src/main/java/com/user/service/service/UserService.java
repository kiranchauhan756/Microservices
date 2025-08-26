package com.user.service.service;

import com.user.service.converter.Converter;
import com.user.service.entity.User;
import com.user.service.entity.UserRequest;
import com.user.service.entity.UserResponse;
import com.user.service.exception.GenericValidationException;
import com.user.service.exception.NoDataFoundException;
import com.user.service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final Converter converter;

    public UserService(UserRepository userRepo, Converter converter) {
        this.userRepo = userRepo;
        this.converter = converter;
    }
//test completed
    public UserResponse save(UserRequest userRequest) {
        Optional<User> existingUser = userRepo.findById(userRequest.getId());
        if (existingUser.isEmpty()) {
            User user = (User) converter.convert(userRequest, new User());
            User savedUser = userRepo.saveUser(user);

            return (UserResponse) converter.convert(savedUser, new UserResponse());
        } else {
            throw new GenericValidationException("User with this id already exists try wth another one");
        }
    }
//test completed
    public List<UserResponse> findAllUsers() {
          Optional<List<User>> users= Optional.of(userRepo.findAllUsers());
          if(!users.get().isEmpty())
              return users.get().stream().map(user-> (UserResponse) converter.convert(user,new UserResponse())).collect(Collectors.toList());
              else{
            throw new NoDataFoundException("Currently there is no user exists in the system");
        }
    }
//test completed
    public UserResponse findById(long id) {
        Optional<User> user=userRepo.findById(id);
        if(user.isPresent()) return (UserResponse) converter.convert(user.get(),new UserResponse());

        else{
        throw new NoDataFoundException("user with this id doesn't exist");
        }
    }
//test completed
    public UserResponse updateUser(long id, UserRequest userRequest) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setFirstName(userRequest.getFirstName());
            user1.setLastName(userRequest.getLastName());
            user1.setEmail(userRequest.getEmail());
            User savedUser = userRepo.updateUser(user1);
            return (UserResponse) converter.convert(savedUser, new UserResponse());
        } else {
            throw new NoDataFoundException("user with this id doesn't exist");
        }
    }
//test completed
    public void deleteUser(long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.delete(user.get());
        } else {
            throw new NoDataFoundException("user with this id doesn't exist");
        }
    }
//test completed
    public UserResponse findByFirstName(String firstName) {
        Optional<User> user=userRepo.findByFirstName(firstName);
        if(user.isPresent()) return (UserResponse) converter.convert(user.get(),new UserResponse());

        else{
            throw new NoDataFoundException("user with this firstName doesn't exist");
        }
    }
//test progress
    public UserResponse updateUserPartially(long id, UserRequest userRequest) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            User user1 = user.get();
            if (userRequest.getFirstName() != null) user1.setFirstName(userRequest.getFirstName());
            if (userRequest.getLastName() != null) user1.setLastName(userRequest.getLastName());
            if (userRequest.getEmail() != null) user1.setEmail(userRequest.getEmail());
            User savedUser = userRepo.updateUser(user1);
            return (UserResponse) converter.convert(savedUser, new UserResponse());
        } else {
            throw new NoDataFoundException("user with this id does not exist");
        }
    }
}

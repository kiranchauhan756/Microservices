package com.user.service.service;

import com.user.service.converter.Converter;
import com.user.service.entity.User;
import com.user.service.entity.UserRequest;
import com.user.service.entity.UserResponse;
import com.user.service.exception.GenericValidationException;
import com.user.service.exception.NoDataFoundException;
import com.user.service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private Converter converter;
    @InjectMocks
    private UserService userService;

  // This test case will check if a user is added successfully to the system after required field checks
   @Test
    void addUser_Success(){
       UserRequest userRequest=UserRequest.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
       User user= User.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
       UserResponse userResponse=UserResponse.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
       when(userRepository.findById(userRequest.getId())).thenReturn(Optional.empty());
       when(converter.convert(any(UserRequest.class),any(User.class))).thenReturn(user);
       when(converter.convert(any(User.class),any(UserResponse.class))).thenReturn(userResponse);
       when(userRepository.saveUser(user)).thenReturn(user);
       UserResponse response=userService.save(userRequest);
       assertThat(response).isNotNull();
       assertThat(response.getFirstName()).isEqualTo("Kiran");
   }
   // This test will check if there is a user already exist with the user id that are trying to create a new user
   @Test
    void addUser_duplicateUser_ThrowsException() throws Exception{
    UserRequest userRequest=UserRequest.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
    User user=User.builder().id(1L).firstName("Pooja").lastName("Chauhan").build();
    when(userRepository.findById(userRequest.getId())).thenReturn(Optional.ofNullable(user));
    assertThrows(GenericValidationException.class,()->userService.save(userRequest));
   }
   // This test will check if the findAllUsers method is working properly
   @Test
   void findAllUsers_Success(){
       User user1=User.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
       User user2=User.builder().id(2L).firstName("Suman").lastName("Sharma").build();
       User user3=User.builder().id(1L).firstName("Raman").lastName("Singh").build();
       UserResponse userResponse1=UserResponse.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
       UserResponse userResponse2=UserResponse.builder().id(2L).firstName("Suman").lastName("Sharma").build();
       UserResponse userResponse3=UserResponse.builder().id(1L).firstName("Raman").lastName("Singh").build();
       when(converter.convert(user1,new UserResponse())).thenReturn(userResponse1);
       when(converter.convert(user2,new UserResponse())).thenReturn(userResponse2);
       when(converter.convert(user3,new UserResponse())).thenReturn(userResponse3);
       when(userRepository.findAllUsers()).thenReturn(List.of(user1,user2,user3));
       List<UserResponse> userResponsesList=userService.findAllUsers();
       assertThat(userResponsesList).isNotEmpty().hasSize(3);
   }
   //This test will check if the findAllUsers is working properly when there no user exists
    @Test
    void findAllUsers_ThrowsException(){
       when(userRepository.findAllUsers()).thenReturn(Collections.emptyList());
       assertThrows(NoDataFoundException.class,()-> userService.findAllUsers());
    }
    //This test will check if the user with the id exists
    @Test
    void findUserById_Success(){
     User user=User.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
     UserResponse userResponse=UserResponse.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
     when(userRepository.findById(1L)).thenReturn(Optional.of(user));
     when(converter.convert(any(User.class),any(UserResponse.class))).thenReturn(userResponse);
     UserResponse userResponse1=userService.findById(1L);
     assertThat(user).isNotNull();
     assertThat(user.getId()).isEqualTo(1L);
    }
    //This test will check if the user with id does not exist
    @Test
    void findUserById_ThrowsException(){
       when(userRepository.findById(1L)).thenReturn(Optional.empty());
       assertThrows(NoDataFoundException.class,()->userService.findById(1L));
    }
    // This test will check if update user is working correctly or not
    @Test
    void updateUser_Success(){
          UserRequest userRequest=UserRequest.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
          User user=User.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
          when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
          UserResponse userResponse=UserResponse.builder().id(1L).firstName("Naman").lastName("Sharma").build();
          when(converter.convert(any(User.class),any(UserResponse.class))).thenReturn(userResponse);
          when(userRepository.updateUser(user)).thenReturn(user);
          UserResponse userResponse1=userService.updateUser(1L,userRequest);
          assertThat(userResponse1).isNotNull();
          assertThat(userResponse1.getId()).isEqualTo(1L);
    }
    //This test will check if for update user, the user does not exist
    @Test
    void updateUser_ThrowsException(){
       UserRequest userRequest=UserRequest.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
       User user=User.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
       when(userRepository.findById(1L)).thenReturn(Optional.empty());
       assertThrows(NoDataFoundException.class,()-> userService.updateUser(1L,userRequest));
    }
    //This test will check if delete User get success or not
    @Test
    void deleteUser_Success(){
       User user=User.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
       when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
       assertDoesNotThrow(()-> userService.deleteUser(1L));
    }
    //This test will check if delete User does not exist
    @Test
    void deleteUser_ThrowsException(){
       when(userRepository.findById(1L)).thenReturn(Optional.empty());
       assertThrows(NoDataFoundException.class,()->userService.deleteUser(1L));
    }
    //This test will check if user with firstName exists
    @Test
    void findUserByFirstName_Success(){
       User user=User.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
       UserResponse userResponse=UserResponse.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
       when(userRepository.findByFirstName(userResponse.getFirstName())).thenReturn(Optional.of(user));
       when(converter.convert(any(User.class),any(UserResponse.class))).thenReturn(userResponse);
       UserResponse userResponse1=userService.findByFirstName("Kiran");
       assertThat(user).isNotNull();
       assertThat(user.getFirstName()).isEqualTo("Kiran");
    }
    //This test will check if user with firstName does not exist
    @Test
    void findUserByFirstName_ThrowsException(){
       when(userRepository.findByFirstName("Kiran")).thenReturn(Optional.empty());
       assertThrows(NoDataFoundException.class,()->userService.findByFirstName("Kiran"));
    }
    //This test will check if partial update for the user is working fine & if the user also exist
    @Test
    void updateUserPartially_Success(){
       UserRequest userRequest=UserRequest.builder().id(1L).firstName("Rita").build();
       User user=User.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
       User updatedUser=User.builder().id(1L).firstName("Rita").build();
       when(userRepository.findById(userRequest.getId())).thenReturn(Optional.ofNullable(user));
       UserResponse userResponse= UserResponse.builder().id(1L).firstName("Rita").build();
       when(converter.convert(any(User.class),any(UserResponse.class))).thenReturn(userResponse);
       UserResponse userResponse1=userService.updateUserPartially(1L,userRequest);
       assertThat(userResponse1).isNotNull();
       assertThat(userResponse1.getId()).isEqualTo(1L);
    }
}

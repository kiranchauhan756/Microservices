package com.user.service.controller;

import com.user.service.BaseUnitTest;
import com.user.service.entity.UserRequest;
import com.user.service.entity.UserResponse;
import com.user.service.exception.ErrorResponse;
import com.user.service.exception.NoDataFoundException;
import com.user.service.exception.UserAlreadyExistsException;
import com.user.service.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static com.user.service.Helper.asJsonString;
import static com.user.service.Helper.asObject;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class ControllerTest extends BaseUnitTest {
    private static final String USER_REQUEST_PATH = "/user";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    // Add user
    @Test
    void addUser_WhenValidationIsValidAndUserDoesNotExistAlready() throws Exception {
        UserRequest userRequest = UserRequest.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
        UserResponse userResponse = UserResponse.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
        when(userService.save(userRequest)).thenReturn(userResponse);
        // creating Http post request
        RequestBuilder request = post(USER_REQUEST_PATH).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(userRequest));
        MvcResult mvcResult = mockMvc.perform(request).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1L)).andExpect(jsonPath("$.firstName").value("Kiran")).andExpect(jsonPath("$.lastName").value("Chauhan")).andReturn();
        String response =mvcResult.getResponse().getContentAsString();
        UserResponse userResponse1=asObject(response, UserResponse.class);
        assertThat(userResponse1.getId()).isEqualTo(userRequest.getId());
    }


     @Test
     void addUser_WhenUserInputIsNotValid() throws Exception{
        UserRequest userRequest=UserRequest.builder().firstName("Kiran").lastName("Chauhan").build();
        RequestBuilder requestBuilder=post(USER_REQUEST_PATH).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(userRequest));
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").exists()).andReturn();
        String response=mvcResult.getResponse().getContentAsString();
         ErrorResponse errorResponse=asObject(response,ErrorResponse.class);
         assertThat(errorResponse.getErrorCode()).isEqualTo("AUTHN-003");
     }

     @Test
     void addUser_WhenDuplicateUserExists() throws Exception{
        UserRequest userRequest=UserRequest.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
        when(userService.save(userRequest)).thenThrow(UserAlreadyExistsException.class);
        RequestBuilder requestBuilder=post(USER_REQUEST_PATH).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(asJsonString(userRequest));
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(status().isFound())
                .andExpect(jsonPath("$.errorCode").exists()).andReturn();
        String response =mvcResult.getResponse().getContentAsString();
        ErrorResponse errorResponse=asObject(response,ErrorResponse.class);
        assertThat(errorResponse.getErrorCode()).isEqualTo("AUTHN-001");

     }

     //delete user
    @Test
    void deleteUser_WhenUserExist() throws Exception {
        doNothing().when(userService).deleteUser(1);
        RequestBuilder requestBuilder=delete(USER_REQUEST_PATH+"/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(status().isNoContent()).andReturn();
    }
    @Test
    void deleteUser_WhenUserDoesNotExist() throws Exception{
        doThrow(NoDataFoundException.class).when(userService).deleteUser(1);
        RequestBuilder  requestBuilder=delete(USER_REQUEST_PATH+"/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").exists()).andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        ErrorResponse errorResponse=asObject(response,ErrorResponse.class);
        assertThat(errorResponse.getErrorCode()).isEqualTo("AUTHN-002");
    }
    //find user by id
    @Test
    void findUserById_WhenUserExists() throws Exception{
        RequestBuilder requestBuilder=get(USER_REQUEST_PATH+"/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        UserResponse userResponse=UserResponse.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
        when(userService.findById(1L)).thenReturn(userResponse);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        String response =mvcResult.getResponse().getContentAsString();
        UserResponse userResponse1=asObject(response,UserResponse.class);
        assertThat(userResponse1.getId()).isEqualTo(1L);
    }
    @Test
    void findByUserId_WhenUserDoesNotExists() throws Exception{
        RequestBuilder requestBuilder=get(USER_REQUEST_PATH+"/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        when(userService.findById(1L)).thenThrow(NoDataFoundException.class);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").exists()).andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        ErrorResponse errorResponse=asObject(response, ErrorResponse.class);
        assertThat(errorResponse.getErrorCode()).isEqualTo("AUTHN-002");
    }
    //find user by firstName
    @Test
    void findUserByFirstName_WhenUserExists() throws Exception{
        RequestBuilder requestBuilder=get(USER_REQUEST_PATH+"?firstName=Kiran").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        UserResponse userResponse=UserResponse.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
        when(userService.findByFirstName("Kiran")).thenReturn(userResponse);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        String response =mvcResult.getResponse().getContentAsString();
        UserResponse userResponse1=asObject(response,UserResponse.class);
        assertThat(userResponse1.getFirstName()).isEqualTo("Kiran");
    }
    @Test
    void findByUserFirstName_WhenUserDoesNotExists() throws Exception{
        RequestBuilder requestBuilder=get(USER_REQUEST_PATH+"?firstName=Kiran").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        when(userService.findByFirstName("Kiran")).thenThrow(NoDataFoundException.class);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").exists()).andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        ErrorResponse errorResponse=asObject(response, ErrorResponse.class);
        assertThat(errorResponse.getErrorCode()).isEqualTo("AUTHN-002");
    }
    //update user
    @Test
    void updateUser_whenUserExists() throws Exception{
     UserRequest userRequest=UserRequest.builder().id(1L).firstName("Naman").lastName("Singh").build();
        RequestBuilder requestBuilder=put(USER_REQUEST_PATH+"/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userRequest)).accept(MediaType.APPLICATION_JSON);
        UserResponse userResponse=UserResponse.builder().id(1L).firstName("Naman").lastName("Singh").build();
        when(userService.updateUser(1L,userRequest)).thenReturn(userResponse);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Naman"))
                .andExpect(jsonPath("$.lastName").value("Singh"))
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        UserResponse userResponse1=asObject(response, UserResponse.class);
        assertThat(userResponse1.getId()).isEqualTo(userRequest.getId());
    }
    @Test
    void updateUser_WhenUserDoesNotExist() throws Exception{
        UserRequest userRequest=UserRequest.builder().id(1L).firstName("Kiran").lastName("Chauhan").build();
        RequestBuilder requestBuilder=put(USER_REQUEST_PATH+"/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userRequest)).accept(MediaType.APPLICATION_JSON);
        when(userService.updateUser(1L,userRequest)).thenThrow(NoDataFoundException.class);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").exists()).andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        ErrorResponse errorResponse=asObject(response, ErrorResponse.class);
        assertThat(errorResponse.getErrorCode()).isEqualTo("AUTHN-002");
    }
    //update user partially
    @Test
    void updatedUserPartially_WhenUserExists() throws Exception{
        UserRequest userRequest=UserRequest.builder().id(1L).lastName("Chauhan").build();
        RequestBuilder requestBuilder=patch(USER_REQUEST_PATH+"/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userRequest)).accept(MediaType.APPLICATION_JSON);
        UserResponse userResponse=UserResponse.builder().id(1L).lastName("Chauhan").build();
        when(userService.updateUserPartially(1L,userRequest)).thenReturn(userResponse);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Chauhan"))
                .andReturn();
        String response=mvcResult.getResponse().getContentAsString();
        UserResponse userResponse1=asObject(response, UserResponse.class);
        assertThat(userResponse1.getId()).isEqualTo(userRequest.getId());
    }
    @Test
    void updateUserPartially_WhenUserDoesNotExist() throws Exception{
        UserRequest userRequest = UserRequest.builder().id(1L).lastName("Kumar").build();
        RequestBuilder requestBuilder=patch(USER_REQUEST_PATH+"/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userRequest)).accept(MediaType.APPLICATION_JSON);
        when(userService.updateUserPartially(1L,userRequest)).thenThrow(NoDataFoundException.class);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorCode").exists()).andReturn();
        String response =mvcResult.getResponse().getContentAsString();
        ErrorResponse errorResponse=asObject(response, ErrorResponse.class);
        assertThat(errorResponse.getErrorCode()).isEqualTo("AUTHN-002");
    }
}

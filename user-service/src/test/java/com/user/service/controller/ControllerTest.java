package com.user.service.controller;

import com.user.service.BaseUnitTest;
import com.user.service.service.UserService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@WebMvcTest(UserController.class)
public class ControllerTest extends BaseUnitTest {
    private static final String CLIENT_REQUEST_PATH="/user";
    @MockitoBean
    private UserService userService;

}

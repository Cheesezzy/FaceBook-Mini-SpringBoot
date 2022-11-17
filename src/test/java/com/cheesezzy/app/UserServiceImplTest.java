package com.cheesezzy.app;

import com.cheesezzy.app.entity.ApiResponse;
import com.cheesezzy.app.entity.User;
import com.cheesezzy.app.repository.UserRepository;

import com.cheesezzy.app.service.serviceimpl.UserServiceImpl;
import com.cheesezzy.app.service.serviceimpl.RegistrationValidationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    RegistrationValidationServiceImpl registrationValidationServiceMock;

    @Mock
    UserRepository userRepositoryMock;


    @InjectMocks
    UserServiceImpl userServiceMock;

    @Test
    void create() {
        User user = new User();

        user.setEmail("pachi@gmail.com");
        user.setPassword("123456");
        user.setFirstName("Patrick");
        user.setLastName("Chidubem");

        when(registrationValidationServiceMock.isFirstNameValid(user)).thenReturn(true);
        when(registrationValidationServiceMock.isSurnameValid(user)).thenReturn(true);
        when(registrationValidationServiceMock.isEmailValid(user)).thenReturn(true);
        when(registrationValidationServiceMock.emailExists(user)).thenReturn(true);
        when(registrationValidationServiceMock.isPasswordValid(user)).thenReturn(true);

        when(userRepositoryMock.save(user)).thenReturn(user);


        ApiResponse<String> response = userServiceMock.registerNewUser(user);

        System.out.println(response.getMessage());

    }

}

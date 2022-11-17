package com.cheesezzy.app.service.serviceimpl;

import com.cheesezzy.app.repository.UserRepository;
import com.cheesezzy.app.service.UserService;
//import com.cheesezzy.app.validators.RegistrationValidationService;
import com.cheesezzy.app.entity.ApiResponse;
import com.cheesezzy.app.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RegistrationValidationServiceImpl registrationValidationService;

    @Override
    public ApiResponse<String> registerNewUser(User user) {
        boolean result = registrationValidationService.isFirstNameValid(user) && (registrationValidationService.isSurnameValid(user))
                &&(registrationValidationService.isEmailValid(user))&&(registrationValidationService.emailExists(user))&&(registrationValidationService.isPasswordValid(user));

        if(result) {
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            return new ApiResponse<>(String.valueOf(true), true, "Signup successful");
        } else
            return new ApiResponse<>(String.valueOf(false), false, "SIGNUP FAILED");
    }

}

package com.cheesezzy.app.service.serviceimpl;

import com.cheesezzy.app.entity.User;
import com.cheesezzy.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RegistrationValidationServiceImpl {

    private final UserRepository userRepository;

    public boolean isFirstNameValid(User user) {
        return user.getFirstName() != null && user.getFirstName().length() >= 3
                && !user.getFirstName().contains("@!*&^%$)([]{}\"\',><");
    }

    public boolean isSurnameValid(User user) {
        return user.getLastName() != null && user.getLastName().length() >= 3
                && !user.getLastName().contains("@!*&^%$)([]{}\"\',><");
    }

    public  boolean isPasswordValid(User user) {
        return user.getPassword() != null && user.getPassword().length() >= 7;
    }

    public boolean isEmailValid(User user) {
//        String regex = "^(.+)@(\\S+) $";
        return user.getEmail() != null &&
                user.getEmail().contains("@");
    }

    public boolean emailExists(User user) {
        return userRepository.findByEmail(user.getEmail()).isEmpty();
    }
}

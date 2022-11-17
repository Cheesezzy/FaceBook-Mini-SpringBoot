package com.cheesezzy.app.service.serviceimpl;

import com.cheesezzy.app.entity.ApiResponse;
import com.cheesezzy.app.entity.User;
import com.cheesezzy.app.repository.LoginRepository;
import com.cheesezzy.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginRepository loginRepository;

    @Override
    public ApiResponse<User> loginWithEmailAndPassword(String email, String password) {
        Optional<User> optionalUser = loginRepository.findByEmailAndPassword(email, password);
        if(optionalUser.isEmpty())
            return new ApiResponse<>("Wrong email or password", false, null);
        else return new ApiResponse<>("Login Successful", true, optionalUser.get());
    }
}

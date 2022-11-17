package com.cheesezzy.app.service;

import com.cheesezzy.app.entity.ApiResponse;
import com.cheesezzy.app.entity.User;

public interface LoginService {
    ApiResponse<User> loginWithEmailAndPassword(String email, String password);
}

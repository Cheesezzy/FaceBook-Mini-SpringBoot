package com.cheesezzy.app.service;

import com.cheesezzy.app.entity.ApiResponse;
import com.cheesezzy.app.entity.User;

public interface UserService {
    ApiResponse<String> registerNewUser(User user);
}

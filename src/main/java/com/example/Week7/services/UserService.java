package com.example.Week7.services;

import com.example.Week7.entities.User;
import com.example.Week7.pojos.ApiResponse;
import com.example.Week7.pojos.UserDto;
import com.example.Week7.repositories.UserRepository;
import com.example.Week7.util.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final  ResponseManager responseManager;
    private  final UserRepository userRepository;

    public ApiResponse createUser(UserDto request) {

        if (request.getName() == null)
            return responseManager.error("Name required!");
        else if (request.getEmail() == null)
            return responseManager.error("Email required!");
        else if (request.getPassword() == null)
            return responseManager.error("Password required!");

        //validate if email address if correct

        //validate if user already exist
        boolean userExist = userRepository.existsByEmail(request.getEmail());
        if(userExist)
            return responseManager.error("User already exist!");
        //save user in database if not exist

        User newUser= new User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setUuid("hdjhdjahjhajhaj");
        newUser.setCreatedAt(new Date()); // change this to auto generated in the entity

        User savedUser = userRepository.save(newUser);
        return responseManager.success(savedUser);
    }

    public  ApiResponse findAllUsers(){
        List<User> userList = userRepository.findAll();
        if(userList.isEmpty())
            return responseManager.success(userList);

        return  responseManager.error("No user available");
    }

    public  ApiResponse findById(Long id){
        User user = userRepository.findById(id).orElse(null);
        if(user!=null)
            return responseManager.success(user);

        return  responseManager.error("User not found!");
    }
}

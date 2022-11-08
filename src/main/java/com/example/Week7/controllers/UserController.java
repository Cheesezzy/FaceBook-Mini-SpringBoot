package com.example.Week7.controllers;

import com.example.Week7.pojos.ApiResponse;
import com.example.Week7.pojos.UserDto;
import com.example.Week7.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse<String> createUser(@RequestBody UserDto request){
        return userService.createUser(request);
    }

    @GetMapping("/list")
    public ApiResponse<String> findAllUsers(){
        return userService.findAllUsers();
    }


    @GetMapping("/{id}")
    public ApiResponse<String> findUserById(@PathVariable Long id){
        return userService.findById(id);
    }


}

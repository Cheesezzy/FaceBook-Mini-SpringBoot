package com.example.Week7.controllers;

import com.example.Week7.pojos.ApiResponse;
import com.example.Week7.pojos.UserDto;
import com.example.Week7.services.UserService;
import com.sun.net.httpserver.Headers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ApiResponse createUser(@RequestBody UserDto request){
        return userService.createUser(request);
    }

    @GetMapping("/list")
    public ApiResponse findAllUsers(){
        return userService.findAllUsers();
    }


    @GetMapping("/{id}")
    public ApiResponse findUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/get_by_email")
    public ApiResponse findUserByEmail(@RequestParam("email") String email){
        return userService.findByEmail(email);
    }

    @GetMapping("/search")
    public ApiResponse findUserBySearch(@RequestParam("q") String question){
        return userService.findBySearch(question);
    }


}

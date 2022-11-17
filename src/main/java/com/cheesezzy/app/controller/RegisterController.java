package com.cheesezzy.app.controller;

import com.cheesezzy.app.entity.ApiResponse;
import com.cheesezzy.app.entity.User;
import com.cheesezzy.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserService userService;

    @GetMapping
    public ModelAndView getRegisterPage() {
        ModelAndView registerView = new ModelAndView("register");
        User user = new User();
        registerView.addObject("user", user);
        return registerView;
    }

    @PostMapping
    public String registerNewUser(@ModelAttribute User user, Model model) {
        ApiResponse<String> apiResponse = userService.registerNewUser(user);
       if(!apiResponse.getStatus())  {
           model.addAttribute("error", apiResponse.getMessage());
           return "register";
       }
        else return "redirect:/login";
    }
}

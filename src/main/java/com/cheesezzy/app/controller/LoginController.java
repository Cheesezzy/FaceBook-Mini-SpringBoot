package com.cheesezzy.app.controller;

import com.cheesezzy.app.entity.ApiResponse;
import com.cheesezzy.app.entity.User;
import com.cheesezzy.app.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping()
    public ModelAndView displayLoginPage() {
        return new ModelAndView("login");
    }

    @PostMapping
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        ApiResponse<User> userOptional = loginService.loginWithEmailAndPassword(email, password);
        if(userOptional.getData() == null) {
            model.addAttribute("error", userOptional.getMessage());
            return "login";
        }
        else {
            session.setAttribute("user", userOptional.getData());
            session.setAttribute("showInputField", false);
            return "redirect:/";
        }
    }
}

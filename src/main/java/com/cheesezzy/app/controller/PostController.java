package com.cheesezzy.app.controller;

import com.cheesezzy.app.entity.Post;
import com.cheesezzy.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value= {"/allPosts", "/"})
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping
    public ModelAndView getPosts(HttpSession session) {
        if(session.getAttribute("user") == null) {
            ModelAndView loginView = new ModelAndView("login");
            return loginView;
        }
        ModelAndView homeView = new ModelAndView("homepage");
        List<Post> posts = postService.getPosts();
        homeView.addObject("posts", posts);
        return homeView;
    }

    @PostMapping("/addNewPost")
    public String addNewPost(@RequestParam String postBody, @RequestParam Long userId) {
        postService.addNewPost(postBody, userId);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String editCommand(HttpSession session) {
        Object checkField = session.getAttribute("showInputField");
        session.setAttribute("showInputField", !(Boolean) checkField);
        return "redirect:/";
    }

    @RequestMapping("/delete") //Validation required
    public String deletePost(@RequestParam Long postId, @RequestParam Long userId) {
        postService.deletePostById(postId, userId);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updatePost(@RequestParam Long postId, @RequestParam Long userId, @RequestParam String postBody, HttpSession session) {
        session.setAttribute("showInputField", false);
        postService.updatePost(postId, userId, postBody);
        return "redirect:/";
    }
}

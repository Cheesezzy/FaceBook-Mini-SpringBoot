package com.cheesezzy.app.controller;

import com.cheesezzy.app.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/like")
public class LikeController {
    @Autowired
    LikeService likeService;

    @GetMapping
    public String getLikes() {
        return "homepage";
    }

    @PostMapping
    public String likePost(@RequestParam Long postId, @RequestParam Long userId) {
        likeService.likePost(postId, userId);
        return "redirect:/";
    }

}

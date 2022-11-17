package com.cheesezzy.app.controller;

import com.cheesezzy.app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping
    public String getComments() {
        return "redirect:/home";
    }

    @PostMapping
    public String addNewComment(@RequestParam Long postId, @RequestParam Long userId, @RequestParam String comment) {
        commentService.addNewComment(postId, userId, comment);
        return "redirect:/";
    }

    @RequestMapping("/delete/{userId}/comment/{commentId}") //Validation required
    public String deleteComment(@PathVariable Long userId, @PathVariable Long commentId) {
        commentService.deleteCommentById(userId, commentId);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updateComment(@RequestParam Long postId, @RequestParam Long userId,
                             @RequestParam Long commentId, @RequestParam String comment, HttpSession session) {
        session.setAttribute("showInputField", false);
        System.out.println(commentService.updateComment(postId, userId, commentId, comment).getData());
        return "redirect:/";
    }

    @RequestMapping("/edit")
    public String toggleInputFieldCommand(HttpSession session) {
        Object checkField = session.getAttribute("showInputField");
        session.setAttribute("showInputField", !(Boolean) checkField);
        return "redirect:/";
    }

}

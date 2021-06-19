package org.serj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopicController {

    @GetMapping("/comments/{id}")
    public String showComments(){

        return "comments";
    }

}

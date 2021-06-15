package org.serj.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PresentationController {
    @GetMapping("about-us")
    public String presentation() {
        return "../static/about-us";
    }
}

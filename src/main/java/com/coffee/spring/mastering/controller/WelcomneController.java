package com.coffee.spring.mastering.controller;

import com.coffee.spring.mastering.domain.WelcomeBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomneController {

    private static final String HELLO_WORLD = "Hello World, %s!";

    @GetMapping("/welcome")
    public String welcome() {
        return "Hello World";
    }

    @GetMapping("/welcome-with-object")
    public WelcomeBean welcomeWithObject() {
        return WelcomeBean.builder()
                .message("Hello World")
                .build();
    }

    @GetMapping("/welcome/name/{name}")
    public WelcomeBean welcomeWithParameter(@PathVariable String name) {
        return WelcomeBean.builder()
                .message(String.format(HELLO_WORLD, name))
                .build();
    }

}

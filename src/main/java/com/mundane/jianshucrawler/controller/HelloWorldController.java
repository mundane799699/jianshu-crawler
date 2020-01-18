package com.mundane.jianshucrawler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloWorldController {
    @GetMapping("/index")
    public String index() {
        return "hello world";
    }
}

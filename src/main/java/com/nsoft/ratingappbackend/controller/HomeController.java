package com.nsoft.ratingappbackend.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class HomeController {

    @GetMapping(path = "public")
    public String home() {
        return "Welcome to public.";
    }

    @GetMapping(path = "private")
    public String privateRoute() {
        return "You are authorized.";
    }

}
package com.example.chillflix.controllers;

import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("api/admin")//end point
public class AdminLoginController {

    @GetMapping
    public Boolean getLoggedIn(@RequestParam("username") String username, @RequestParam("password") String password){
        String username1 = "ChillFlix";
        String password1 = "ChillFlix:1505";
        return username1.equals(username) && password1.equals(password);
    }
}

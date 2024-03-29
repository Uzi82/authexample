package org.authexample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {
    @GetMapping("/all")
    public String  forAll () {
        return "For all";
    }
    @GetMapping("/auth")
    public String  forAuth () {
        return "For auth";
    }
    @GetMapping("/admin")
    public String  forAdmin () {
        return "For admins";
    }
}

package com.example.demo.controllers;

import com.example.demo.models.registerDTO;
import com.example.demo.repositories.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class accountsController {

    @Autowired
    private userrepo repo;

    @GetMapping("/register")
    public String register(Model model) {
        registerDTO registerDto = new registerDTO();
        model.addAttribute(registerDto);
        return "register";
    }
}

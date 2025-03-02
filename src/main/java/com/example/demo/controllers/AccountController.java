package com.example.demo.controllers;

import com.example.demo.models.AppUser;
import com.example.demo.models.RegisterDTO;
import com.example.demo.repositories.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    private AppUserRepository repo;

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDTO registerDTO = new RegisterDTO();
        model.addAttribute(registerDTO);
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute RegisterDTO registerDTO, BindingResult result) {

        //if wrong password.
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            result.addError(
                    new FieldError("registerDTO", "confirmPassword", "Passwords do not match.")
            );
        }

        //if email already exists.
        AppUser appUser = repo.findByEmail(registerDTO.getEmail());
        if (appUser != null) {
            result.addError(
                    new FieldError("registerDTO", "email", "Email is already used.")
            );
        }

        return "register";
    }
}

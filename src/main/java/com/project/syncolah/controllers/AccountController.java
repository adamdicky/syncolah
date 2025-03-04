package com.project.syncolah.controllers;

import com.project.syncolah.models.AppUser;
import com.project.syncolah.models.RegisterDTO;
import com.project.syncolah.repositories.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class AccountController {

    @Autowired
    private AppUserRepository repo;

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDTO registerDTO = new RegisterDTO();
        model.addAttribute(registerDTO);
        model.addAttribute("success", false);
        return "register"; //return tu register.html
    }

    @PostMapping("/register")
    public String register(Model model, @Valid @ModelAttribute RegisterDTO registerDTO, BindingResult result) {

        //if wrong matched password.
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

        if (result.hasErrors()) {
            return "register";
        }

        //create account
        try {
            var bCryptEncoder = new BCryptPasswordEncoder();

            AppUser newUser = new AppUser();
            newUser.setFirstName(registerDTO.getFirstName());
            newUser.setLastName(registerDTO.getLastName());
            newUser.setEmail(registerDTO.getEmail());
            newUser.setPhone(registerDTO.getPhone());
            newUser.setRole("Client");
            newUser.setCreatedAt(new Date());
            newUser.setPassword(bCryptEncoder.encode(registerDTO.getPassword()));

            repo.save(newUser);

            model.addAttribute("registerDTO", new RegisterDTO());
            model.addAttribute("success", true);


        } catch (Exception e)
        {
            result.addError(new FieldError("registerDTO", "firstName", e.getMessage()));
        }

        return "register"; //return to register.html
    }
}

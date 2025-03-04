package com.project.syncolah.controllers;

import com.project.syncolah.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {

    @Autowired
    private AppUserRepository repo;

}

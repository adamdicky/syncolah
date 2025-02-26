package com.example.demo.repositories;

import com.example.demo.models.usermodel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userrepo extends JpaRepository<usermodel, Integer> {
    public usermodel findByUsername(String username);
}

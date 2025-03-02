package com.example.demo.repositories;

import com.example.demo.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository  extends JpaRepository<AppUser, Long> {

    public AppUser findByEmail(String email);
}

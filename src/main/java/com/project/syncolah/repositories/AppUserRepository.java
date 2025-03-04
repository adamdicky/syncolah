package com.project.syncolah.repositories;

import com.project.syncolah.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public AppUser findByEmail(String email);
}

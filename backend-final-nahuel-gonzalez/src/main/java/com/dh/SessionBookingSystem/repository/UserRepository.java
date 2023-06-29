package com.dh.SessionBookingSystem.repository;


import com.dh.SessionBookingSystem.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email);
}
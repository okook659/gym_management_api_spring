package com.projetvalidation.gestionSalle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetvalidation.gestionSalle.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
}

package com.example.accountservice.repositories;

import com.example.accountservice.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Page<User> findUsersByUsername(String username, Pageable pageable);

//    List<User> findAllById(UUID id);

    boolean existsByUsername(String username);



}
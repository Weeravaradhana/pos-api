package com.devapp.pos.repository;

import com.devapp.pos.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
import java.util.UUID;
@EnableJpaRepositories
public interface SystemUserRepo extends JpaRepository<SystemUser, UUID> {
    boolean existsByEmail(String email);
    Optional<SystemUser> findSystemUserByEmail(String email);
}
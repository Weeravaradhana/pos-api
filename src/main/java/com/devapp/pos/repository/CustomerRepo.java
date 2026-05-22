package com.devapp.pos.repository;

import com.devapp.pos.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.UUID;

@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer, UUID> {
}

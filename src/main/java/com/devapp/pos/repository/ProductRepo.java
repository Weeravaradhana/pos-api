package com.devapp.pos.repository;

import com.devapp.pos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.UUID;

@EnableJpaRepositories
public interface ProductRepo extends JpaRepository<Product, UUID> {
}

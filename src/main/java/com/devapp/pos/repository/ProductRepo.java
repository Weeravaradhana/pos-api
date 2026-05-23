package com.devapp.pos.repository;

import com.devapp.pos.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.UUID;

@EnableJpaRepositories
public interface ProductRepo extends JpaRepository<Product, UUID> {

    @Query(value = "SELECT * FROM product WHERE description LIKE ?1", nativeQuery = true)
    public Page<Product> findAllProducts(String  SearchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM product WHERE description LIKE ?1", nativeQuery = true)
    public long countAllProducts(String SearchText);
}

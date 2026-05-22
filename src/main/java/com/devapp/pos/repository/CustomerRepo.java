package com.devapp.pos.repository;

import com.devapp.pos.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.util.UUID;

@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer, UUID> {

    @Query(value = "SELECT * FROM customer WHERE name LIKE ?1 OR address LIKE ?1", nativeQuery = true)
    public Page<Customer> countAllCustomers(String SearchText, Pageable pageable);

    @Query(value = "SELECT COUNT(*) FROM customer WHERE name LIKE ?1 OR address LIKE ?1", nativeQuery = true)
    public long countAllCustomers(String SearchText);
}

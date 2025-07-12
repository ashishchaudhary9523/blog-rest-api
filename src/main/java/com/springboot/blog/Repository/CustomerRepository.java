package com.springboot.blog.Repository;

import com.springboot.blog.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer , Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByUserNameOrEmail(String username, String email);
    Optional<Customer> findByUserName(String username);
    Boolean existsCustomerByUserName(String username);
    Boolean existsCustomerByEmail(String email);
}

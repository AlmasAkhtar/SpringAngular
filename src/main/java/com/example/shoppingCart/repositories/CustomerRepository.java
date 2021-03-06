package com.example.shoppingCart.repositories;

import com.example.shoppingCart.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("CustomerRepo")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAll();

    Optional<Customer> findById(Integer integer);

}


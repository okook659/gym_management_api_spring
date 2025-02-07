package com.projetvalidation.gestionSalle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetvalidation.gestionSalle.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    public Optional<Customer> findByLastName(String lastName);
}

package com.projetvalidation.gestionSalle.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetvalidation.gestionSalle.entity.Customer;
import com.projetvalidation.gestionSalle.service.implementations.CustomerService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getByIdCustomer(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{lastName}")
    public ResponseEntity<Customer> getCustomerByLastName(@PathVariable String lastName){
        Customer customer = customerService.getByLastNameCustomer(lastName);
        return ResponseEntity.ok(customer);
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer creatingCustomer){
        Customer createdCustomer = customerService.createCustomer(creatingCustomer);
        return ResponseEntity.ok(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer (@RequestBody Customer updatingCustomer, @PathVariable Long id) {
        updatingCustomer.setId(id);
        Customer updatedCustomer = customerService.updateCustomer(updatingCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer (@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
    
}

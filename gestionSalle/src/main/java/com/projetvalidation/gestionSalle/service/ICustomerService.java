package com.projetvalidation.gestionSalle.service;

import java.util.List;

import com.projetvalidation.gestionSalle.entity.Customer;

public interface ICustomerService {

    public List<Customer> getAllCustomers();

    public Customer getByLastNameCustomer(String lastName);

    public Customer getByIdCustomer(Long id);

    public Customer updateCustomer(Customer updatingCustomer);

    public Customer createCustomer(Customer creatingCustomer);

    public void deleteCustomer(Long id);
}

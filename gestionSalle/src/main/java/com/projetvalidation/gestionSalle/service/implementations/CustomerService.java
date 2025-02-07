package com.projetvalidation.gestionSalle.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projetvalidation.gestionSalle.entity.Customer;
import com.projetvalidation.gestionSalle.repository.CustomerRepository;
import com.projetvalidation.gestionSalle.service.ICustomerService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CustomerService implements ICustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @Override
    public Customer getByLastNameCustomer(String lastName){
        Optional<Customer> foundCustomer = customerRepository.findByLastName(lastName);
        if(foundCustomer.isPresent()){
            return foundCustomer.get();
        }
        else{
            throw new RuntimeException("Customer not found with lastname: "+lastName+"!");
        }
    }

    @Override
    public Customer getByIdCustomer(Long id){
        Optional<Customer> foundCustomer = customerRepository.findById(id);
        if(foundCustomer.isPresent()){
            return foundCustomer.get();
        }
        else{
            throw new RuntimeException("Customer not found with id: "+id+"!");
        }
    }

    @Override
    public Customer createCustomer(Customer creatingCustomer){
        return customerRepository.save(creatingCustomer);
    }


    // @Override
    // public Customer updateCustomer(Customer updatingCustomer){
    //     if(updatingCustomer.getId() != null && customerRepository.existsById(updatingCustomer.getId())){
    //         return customerRepository.save(updatingCustomer);
    //     }
    //     else{
    //         throw new RuntimeException("Couldn't update customer with id:"+ updatingCustomer.getId()+"!");
    //     }
    // }

    @Transactional
    @Override
    public Customer updateCustomer(Customer updatingCustomer) {
        if (updatingCustomer.getId() == null) {
            throw new IllegalArgumentException("Customer ID must not be null for update.");
        }

        return customerRepository.findById(updatingCustomer.getId())
                .map(existingCustomer -> {
                    // Ici, on peut mettre à jour les champs si besoin
                    if(updatingCustomer.getLastName() != null){
                        existingCustomer.setLastName(updatingCustomer.getLastName());
                    }
                    if(updatingCustomer.getFirstName() != null){
                        existingCustomer.setFirstName(updatingCustomer.getFirstName());
                    }
                    if(updatingCustomer.getRegistrationDate() != null){
                        existingCustomer.setRegistrationDate(updatingCustomer.getRegistrationDate());
                    }
                    if(updatingCustomer.getPhoneNumber() != null){
                        existingCustomer.setPhoneNumber(updatingCustomer.getPhoneNumber());
                    }
                    if(updatingCustomer.isActiveSubscription() != (false || true)){
                        existingCustomer.setActiveSubscription(updatingCustomer.isActiveSubscription());
                    }
                    return customerRepository.save(existingCustomer);
                })
                .orElseThrow(() -> new EntityNotFoundException(
                        "Customer with ID " + updatingCustomer.getId() + " not found."));
    }

    @Override
    public void deleteCustomer(Long id){
        Optional<Customer> deletingCustomer = customerRepository.findById(id);
        if(deletingCustomer.isPresent() && customerRepository.existsById(id)){
            customerRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Couldn't delete the customer with id:" + id + "!");
        }
    }

}

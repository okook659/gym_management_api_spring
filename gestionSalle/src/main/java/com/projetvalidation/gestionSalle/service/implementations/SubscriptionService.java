package com.projetvalidation.gestionSalle.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projetvalidation.gestionSalle.entity.Subscription;
import com.projetvalidation.gestionSalle.repository.SubscriptionRepository;
import com.projetvalidation.gestionSalle.service.ISubscriptionService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
public class SubscriptionService implements ISubscriptionService {
    final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription getByIdSubscription(Long id) {
        Optional<Subscription> foundSubscription = subscriptionRepository.findById(id);
        if (foundSubscription.isPresent()) {
            return foundSubscription.get();
        } else {
            throw new RuntimeException("Couldn't find subscription with ID :" + id + "!");
        }
    }

    @Override
    public Subscription createSubscription(Subscription creatingSubscription) {
        return subscriptionRepository.save(creatingSubscription);
    }

    @Transactional
    @Override
    public Subscription updateSubscription(Subscription updatingSubscription){
        if(updatingSubscription.getId() == null){
            throw new IllegalArgumentException("Subscription ID must not be null for update.");
        }
        return subscriptionRepository.findById(updatingSubscription.getId()).map(
            existingSubscription ->{
                if(updatingSubscription.getCustomer() != null){
                    existingSubscription.setCustomer(updatingSubscription.getCustomer());
                }
                if(updatingSubscription.getPack() != null){
                    existingSubscription.setPack(updatingSubscription.getPack());
                }
                if(updatingSubscription.getStart_date() != null){
                    existingSubscription.setStart_date(updatingSubscription.getStart_date());
                }
                return subscriptionRepository.save(existingSubscription);
            }).orElseThrow(() -> new EntityNotFoundException("Subscription with ID: "+ updatingSubscription.getId() + "!"));
    }

    @Override
    public void deleteSubscription(Long id) {
        Optional<Subscription> deletingSubscription = subscriptionRepository.findById(id);
        if (deletingSubscription.isPresent()) {
            subscriptionRepository.deleteById(id);
        } else {
            throw new RuntimeException("Couldn't find subscription with ID: " + id + "!");
        }
    }
}

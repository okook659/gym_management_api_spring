package com.projetvalidation.gestionSalle.service;

import java.util.List;

import com.projetvalidation.gestionSalle.entity.Subscription;

public interface ISubscriptionService {
    public List<Subscription> getAllSubscriptions();
    public Subscription getByIdSubscription(Long id);
    public Subscription createSubscription(Subscription creatingSubscription);
    public Subscription updateSubscription(Subscription updatingSubscription);
    public void deleteSubscription(Long id);
}

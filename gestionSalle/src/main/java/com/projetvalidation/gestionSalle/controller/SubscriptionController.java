package com.projetvalidation.gestionSalle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetvalidation.gestionSalle.service.implementations.SubscriptionService;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
    final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
}

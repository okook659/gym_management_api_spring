package com.projetvalidation.gestionSalle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetvalidation.gestionSalle.entity.Pack;

public interface PackRepository extends JpaRepository<Pack, Long>{
    public Optional<Pack> findByOfferName(String offerName);
}

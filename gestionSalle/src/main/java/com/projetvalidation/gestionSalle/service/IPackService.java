package com.projetvalidation.gestionSalle.service;

import java.util.List;

import com.projetvalidation.gestionSalle.entity.Pack;

public interface IPackService {
    
    public List<Pack> getAllPacks();

    public Pack getByNamePack(String offerName);

    public Pack getByIdPack(Long id);

    public Pack createPack(Pack creatingPack);

    public Pack updatePack(Pack updatingPack);

    public void deletePack(Long id);
}

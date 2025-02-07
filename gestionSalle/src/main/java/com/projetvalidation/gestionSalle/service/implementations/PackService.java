package com.projetvalidation.gestionSalle.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projetvalidation.gestionSalle.entity.Pack;
import com.projetvalidation.gestionSalle.repository.PackRepository;
import com.projetvalidation.gestionSalle.service.IPackService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
public class PackService implements IPackService{

    private PackRepository packRepository;

    public PackService(PackRepository packRepository){
        this.packRepository = packRepository;
    }

    @Override
    public List<Pack> getAllPacks(){
        return packRepository.findAll();
    }

    @Override
    public Pack getByNamePack(String offerName){
        Optional<Pack> foundPack = packRepository.findByOfferName(offerName);
        if(foundPack.isPresent()){
            return foundPack.get();
        }
        else{
            throw new RuntimeException("Pack not found with the name:"+ offerName +"!");
        }
    }

    @Override
    public Pack getByIdPack(Long id){
        Optional<Pack> foundPack = packRepository.findById(id);
        if(foundPack.isPresent()){
            return foundPack.get();
        }
        else{
            throw new RuntimeException("Pack not found with id: "+id+"!");
        }
    }

    @Override
    public Pack createPack(Pack creatingPack){
        return packRepository.save(creatingPack);
    }

    @Transactional
    @Override
    public Pack updatePack(Pack updatingPack){
        if(updatingPack.getId() == null){
            throw new IllegalArgumentException("Pack ID must not be null for update.");
        }
        return packRepository.findById(updatingPack.getId()).map(
            existingPack ->{
                if(updatingPack.getOfferName() != null){
                    existingPack.setOfferName(updatingPack.getOfferName());
                }
                if(updatingPack.getDurationMonths() != null){
                    existingPack.setDurationMonths(updatingPack.getDurationMonths());
                }
                if(updatingPack.getMonthlyPrice() != null){
                    existingPack.setMonthlyPrice(updatingPack.getMonthlyPrice());
                }
                return packRepository.save(existingPack);
            }
        ).orElseThrow(() -> new EntityNotFoundException("Pack with ID "+ updatingPack.getId()+" not found."));
    }

    @Override
    public void deletePack(Long id){
        Optional<Pack> deletingPack = packRepository.findById(id);
        if(deletingPack.isPresent() && packRepository.existsById(id)){
            packRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Couldn't delete the pack with id:" + id + "!");
        }
    }
}

package com.projetvalidation.gestionSalle.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetvalidation.gestionSalle.entity.Pack;
import com.projetvalidation.gestionSalle.service.implementations.PackService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/packs")
public class PackController {

    final PackService packService;

    public PackController(PackService packService) {
        this.packService = packService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Pack>> getAllPacks() {
        List<Pack> packs = packService.getAllPacks();
        return ResponseEntity.ok(packs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pack> getByIdPack(@PathVariable Long id) {
        Pack pack = packService.getByIdPack(id);
        return ResponseEntity.ok(pack);
    }

    @GetMapping("/{offerName}")
    public ResponseEntity<Pack> getByNamePack(@PathVariable String offerName) {
        Pack pack = packService.getByNamePack(offerName);
        return ResponseEntity.ok(pack);
    }

    @PostMapping("/create")
    public ResponseEntity<Pack> createPack(@RequestBody Pack creatingPack) {
        Pack createdPack = packService.createPack(creatingPack);
        return ResponseEntity.ok(createdPack);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pack> updatePack(@PathVariable Long id, @RequestBody Pack updatingPack) {
        updatingPack.setId(id);
        Pack updatedPack = packService.updatePack(updatingPack);
        return ResponseEntity.ok(updatedPack);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pack> deletePack(@PathVariable Long id){
        packService.deletePack(id);
        return ResponseEntity.noContent().build();
    }

}

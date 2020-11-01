package com.techcenter.backend.controllers;


import com.techcenter.backend.models.Spécialité;
import com.techcenter.backend.repositories.SpécialitéRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SpécialitéController {

    @Autowired
    public SpécialitéRepository spécialitéRepository;


    //Liste des spécialités
    @GetMapping(value = "/ListedesSpécialités")
    public List<Spécialité> getAllSpécialités() {
        return spécialitéRepository.findAll();
    }


    //Ajouter une spécialité
    @PostMapping(value ="/AjoutSpécialité")
    public String addSpécialité(@RequestBody Spécialité spécialité) {
        Spécialité insertedSpécialité = spécialitéRepository.insert(spécialité);
        return "La spécialité a été ajoutée avec succès!" ;
    }

    //Supprimer une spécialité par id
    @DeleteMapping(value ="/SupprimerSpécialité/{id}")
    public String deleteSpécialité(@PathVariable("id")   String id)
    {
        spécialitéRepository.deleteSpécialitéById(id);
        return  "La spécialité a été supprimé avec succès";
    }

    //Supprimer tous les spécialités
    @DeleteMapping(value ="/SupprimerSpécialités")
    public String deleteSpécialités()
    {
        spécialitéRepository.deleteAll();
        return  "ddddddddd";
    }


    //Modifier une spécialité
    @PutMapping(value="/UpdateSpécialité/{id}")
    public String updateSpécialité(@RequestBody Spécialité spécialité, @PathVariable String id)
    {
        Optional<Spécialité> spécialitéData = spécialitéRepository.findSpécialitéById(id);
        if(spécialitéData.isPresent()) {
            Spécialité s = spécialitéData.get();
            s.setNom_du_spécialité(spécialité.getNom_du_spécialité());
            spécialitéRepository.save(s);
        }
        return "la spécialité a été modifiée avec succée";
    }






}

package com.techcenter.backend.controllers;


import com.techcenter.backend.models.Formateur;
import com.techcenter.backend.models.Formation;
import com.techcenter.backend.repositories.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FormationController {


    @Autowired
    public FormationRepository formationRepository;


    //Liste des formations
    @GetMapping(value = "/ListedesFormations")
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

  //Liste des formations
    @GetMapping(value = "/getFormationById/{id}")
    public Formation getFormationById(@PathVariable("id")   String id) {
        return formationRepository.findFormationById(id);
    }

    //Supprimer une formation par id
    @DeleteMapping(value ="/SupprimerFormation/{id}")
    public String deleteFormation(@PathVariable("id")   String id)
    {
        formationRepository.deleteFormationsById(id);
        return  "La formation a été supprimée avec succès";
    }

    //Supprimer tous les formations
    @DeleteMapping(value ="/SupprimerFormations")
    public String deleteFormations()
    {
        formationRepository.deleteAll();
        return  "ddddddddd";
    }

    //Ajouter une formation
    @PostMapping(value ="/AjoutFormation")
    public String addFormation(@RequestBody Formation formation) {

        Formation insertedFormation = formationRepository.insert(formation);
        return "La formation a été ajoutée avec succès!" ;
    }
    //Modifier une formation
    @PutMapping(value="/UpdateFormation/{id}")
    public String updateFormation(@RequestBody Formation formation, @PathVariable String id)
    {
        Formation formationData = formationRepository.findFormationById(id);
        if(formationData!=null) {
        	formationData.setTitre(formation.getTitre());
        	formationData.setDescription(formation.getDescription());
        	formationData.setNiveau(formation.getNiveau());
        	formationData.setMatiéres(formation.getMatiéres());
            formationRepository.save(formationData);
        }
        return "La formation a été modifiée avec succée";
    }




}

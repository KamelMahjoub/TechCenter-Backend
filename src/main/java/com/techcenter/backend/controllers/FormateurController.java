package com.techcenter.backend.controllers;

import com.techcenter.backend.models.Formateur;
import com.techcenter.backend.repositories.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FormateurController {

    @Autowired
    public FormateurRepository formateurRepository;


    //Liste des formateurs
    @GetMapping(value = "/ListedesFormateurs")
    public List<Formateur> getAllFormateurs() {
        return formateurRepository.findAll();
    }

    //Supprimer un formateur par cin
    @DeleteMapping(value ="/SupprimerFormateur/{cin}")
    public String deleteFormateur(@PathVariable("cin")   String cin)
    {
        formateurRepository.deleteFormateurByCin(cin);
        return  "Le formateur a été supprimé avec succès";
    }

    //Supprimer tous les formateurs
    @DeleteMapping(value ="/SupprimerFormateurs")
    public String deleteFormateurs()
    {
        formateurRepository.deleteAll();
        return  "ddddddddd";
    }

    //Ajouter un formateur
    @PostMapping(value ="/AjoutFormateur")
    public String addFormateur(@RequestBody Formateur formateur) {

        Formateur insertedFormateur = formateurRepository.insert(formateur);
        return "Le formateur a été ajouté avec succès!" ;
    }
//Modifier un formateur
    @PutMapping(value="/UpdateFormateur/{cin}")
    public String updateFormateur(@RequestBody Formateur formateur, @PathVariable String cin)
    {
        Optional<Formateur> formateurData = formateurRepository.findFormateurByCin(cin);
       if(formateurData.isPresent()) {
           Formateur f = formateurData.get();
           f.setCin(formateur.getCin());
           f.setMot_de_passe(formateur.getMot_de_passe());
           f.setNom(formateur.getNom());
           f.setPrenom(formateur.getPrenom());
           f.setAdresse(formateur.getAdresse());
           f.setDate_de_naissance(formateur.getDate_de_naissance());
           f.setNum_tel(formateur.getNum_tel());
           formateurRepository.save(f);
       }
        return "Le formateur a été modifié avec succée";
    }


}

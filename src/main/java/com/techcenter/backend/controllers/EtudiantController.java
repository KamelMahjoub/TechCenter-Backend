package com.techcenter.backend.controllers;

import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Formateur;
import com.techcenter.backend.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EtudiantController {

    @Autowired
    public EtudiantRepository etudiantRepository;

    //Liste des etudiants
    @GetMapping(value = "/ListedesEtudiants")
    public List<Etudiant> getAllFormateurs() {
        return etudiantRepository.findAll();
    }

 
    @GetMapping(value = "/getEtudiantByCin/{cin}")
    public Etudiant getEtudiantByCin(@PathVariable("cin") String cin) {
        return etudiantRepository.findEtudiantByCin(cin);
    }


    //Supprimer un etudiant par cin
    @DeleteMapping(value = "/SupprimerEtudiant/{cin}")
    public String deleteEtudiant(@PathVariable("cin") String cin) {
        etudiantRepository.deleteEtudiantByCin(cin);
        return "L'étudiant a été supprimé avec succès";
    }

    //Ajouter un étudian t 
    @PostMapping(value = "/AjoutEtudiant")
    public String addEtudiant(@RequestBody Etudiant etudiant) {

        Etudiant insertedEtudiant = etudiantRepository.insert(etudiant);
        return "L'étudiant a été ajouté avec succès!";
    }

    //Modifier un etudiant
    @PutMapping(value="/UpdateEtudiant/{cin}")
    public String updateEtudiant(@RequestBody Etudiant etudiant, @PathVariable String cin)
    {
      Etudiant etudiantData = etudiantRepository.findEtudiantByCin(cin);
        if(etudiantData!=null) {
           
        	etudiantData.setCin(etudiant.getCin());
            etudiantData.setMot_de_passe(etudiant.getMot_de_passe());
            etudiantData.setNom(etudiant.getNom());
            etudiantData.setPrenom(etudiant.getPrenom());
            etudiantData.setAdresse(etudiant.getAdresse());
            etudiantData.setDate_de_naissance(etudiant.getDate_de_naissance());
            etudiantData.setNum_tel(etudiant.getNum_tel());
            etudiantRepository.save(etudiantData);
        }
        return "L'étudiant a été modifié avec succée";
    }

}







package com.techcenter.backend.controllers;

import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Formateur;
import com.techcenter.backend.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EtudiantController {

    @Autowired
    public EtudiantRepository etudiantRepository;

    //Liste des etudiants
    @GetMapping(value = "/ListedesEtudiants")
    public List<Etudiant> getAllFormateurs() {
        return etudiantRepository.findAll();
    }


    //Supprimer un etudiant par cin
    @DeleteMapping(value = "/SupprimerEtudiant/{cin}")
    public String deleteEtudiant(@PathVariable("cin") String cin) {
        etudiantRepository.deleteEtudiantByCin(cin);
        return "L'étudiant a été supprimé avec succès";
    }

    //Ajouter un étudiant
    @PostMapping(value = "/AjoutEtudiant")
    public String addEtudiant(@RequestBody Etudiant etudiant) {

        Etudiant insertedEtudiant = etudiantRepository.insert(etudiant);
        return "L'étudiant a été ajouté avec succès!";
    }

    //Modifier un etudiant
    @PutMapping(value="/UpdateEtudiant/{cin}")
    public String updateEtudiant(@RequestBody Etudiant etudiant, @PathVariable String cin)
    {
        Optional<Etudiant> etudiantData = etudiantRepository.findEtudiantByCin(cin);
        if(etudiantData.isPresent()) {
            Etudiant e = etudiantData.get();
            e.setCin(etudiant.getCin());
            e.setMot_de_passe(etudiant.getMot_de_passe());
            e.setNom(etudiant.getNom());
            e.setPrenom(etudiant.getPrenom());
            e.setAdresse(etudiant.getAdresse());
            e.setDate_de_naissance(etudiant.getDate_de_naissance());
            e.setNum_tel(etudiant.getNum_tel());
            etudiantRepository.save(e);
        }
        return "L'étudiant a été modifié avec succée";
    }

}







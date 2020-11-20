package com.techcenter.backend.controllers;

import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Session;
import com.techcenter.backend.repositories.EtudiantRepository;
import com.techcenter.backend.repositories.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping(value = "/getEtudiantByEmail/{email}")
    public Etudiant getEtudiantByEmail(@PathVariable("email") String email) {
        return etudiantRepository.findEtudiantByEmail(email);
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


    //Liste des sessions par cin
    @GetMapping(value = "/getListeSessionsEtudiant/{cin}")
    public List<Session> getListeSessionsEtudiant(@PathVariable("cin") String cin) {
        List<Session> liste_des_sessions =  etudiantRepository.findEtudiantByCin(cin).getListe_des_session();
        return liste_des_sessions;
    }




}







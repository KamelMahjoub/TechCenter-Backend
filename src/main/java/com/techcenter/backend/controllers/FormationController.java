package com.techcenter.backend.controllers;


import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Formation;
import com.techcenter.backend.repositories.EtudiantRepository;
import com.techcenter.backend.repositories.FormationRepository;
import com.techcenter.backend.services.PushNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.management.Query;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FormationController {
   PushNotification pushNotification = new PushNotification();
    @Autowired
    public FormationRepository formationRepository;
    @Autowired
    public EtudiantRepository etudiantRepository;

    //Liste des formations
    @GetMapping(value = "/ListedesFormations")
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

  //Liste des formations par id
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
/*
        for(Etudiant e : etudiantRepository.findAll())
        {
            pushNotification.sendMessageToUser("Ajouter formation",e.getDeviceId(),insertedFormation);
        }*/


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
        	if(formation.getImage()!=null && formation.getImage().length()>0)
            {
                formationData.setImage(formation.getImage());
            }

        	//formationData.setNiveau(formation.getNiveau());
        	//formationData.setMatiéres(formation.getMatiéres());
            formationRepository.save(formationData);
        }
        return "La formation a été modifiée avec succée";
    }

    //Liste des formations par titre
    @GetMapping(value = "/getFormationByTitre/{titre}")
    public List<Formation> getFormationsByTitre(@PathVariable("titre")   String titre) {

        List<Formation> résultat_de_la_recherche = formationRepository.findFormationsByTitre(titre);
        return résultat_de_la_recherche ;
    }

    //Liste des formations par titre qui contient
    @GetMapping(value = "/getFormationByTitres/{titre}")
    public List<Formation> getFormationsByTitres(@PathVariable("titre")   String titre) {
        List<Formation> résultat_de_la_recherche = formationRepository.findAllByTitreContaining(titre);
        return résultat_de_la_recherche ;
    }



    @PutMapping(value = "/api/imageFormation/{id}")
    public String uploadToLocalFileSystem(@RequestParam("file") MultipartFile file, @PathVariable String id) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileBasePath="/home/dell/Documents/upload/";
        Path path = Paths.get(fileBasePath + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/home/dell/Documents/upload/")
                .path(fileName)
                .toUriString();



        return fileName;
    }

}

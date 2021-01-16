package com.techcenter.backend.controllers;

import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Password;
import com.techcenter.backend.models.Session;
import com.techcenter.backend.repositories.EtudiantRepository;
import com.techcenter.backend.repositories.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    @DeleteMapping(value = "/SupprimerEtudiant/{id}")
    public String deleteEtudiant(@PathVariable("id") String id) {
        etudiantRepository.deleteById(id);
        return "L'étudiant a été supprimé avec succès";
    }

    //Ajouter un étudian t 
    @PostMapping(value = "/AjoutEtudiant")
    public String addEtudiant(@RequestBody Etudiant etudiant) {

        Etudiant insertedEtudiant = etudiantRepository.insert(etudiant);
        return "L'étudiant a été ajouté avec succès!";
    }

    //Modifier un etudiant
    @PutMapping(value="/UpdateEtudiant/{id}")
    public String updateEtudiant(@RequestBody Etudiant etudiant, @PathVariable String id)
    {
      Etudiant etudiantData = etudiantRepository.findEtudiantById(id);
        if(etudiantData!=null) {
        	etudiantData.setCin(etudiant.getCin());
            if(etudiant.getMot_de_passe()!=null){ etudiantData.setMot_de_passe(etudiant.getMot_de_passe());}
            etudiantData.setNom(etudiant.getNom());
            etudiantData.setPrenom(etudiant.getPrenom());
            etudiantData.setAdresse(etudiant.getAdresse());
            etudiantData.setDate_de_naissance(etudiant.getDate_de_naissance());
            etudiantData.setNum_tel(etudiant.getNum_tel());
            etudiantRepository.save(etudiantData);
        }
        return "L'étudiant a été modifié avec succée";
    }
    //Modifier mot de passe d un etudiant
    @PutMapping(value="/UpdatePassword/{id}")
    public String UpdatePassword(@RequestBody Password passwordEtudiant, @PathVariable String id)
    {
        Etudiant etudiantData = etudiantRepository.findEtudiantById(id);
        if(etudiantData!=null)
        {
            if(etudiantData.getMot_de_passe().equals(passwordEtudiant.getOldPassword())) {
                etudiantData.setMot_de_passe(passwordEtudiant.getNewPassword());
                etudiantRepository.save(etudiantData);
            }
        }

        return "L'étudiant a été modifié avec succée";
    }


    @PostMapping(value = "/api/upload")
    public ResponseEntity uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
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
        return ResponseEntity.ok(fileDownloadUri);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
        String fileBasePath="/home/dell/Documents/upload/";
        Path path = Paths.get(fileBasePath + fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }





}







package com.techcenter.backend.controllers;

import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Password;
import com.techcenter.backend.models.Session;
import com.techcenter.backend.repositories.EtudiantRepository;
import com.techcenter.backend.repositories.FormateurRepository;
import com.techcenter.backend.services.SendEmail;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;


@RestController
@CrossOrigin(origins = "*")
public class EtudiantController {

    @Autowired
    public EtudiantRepository etudiantRepository;

    @Autowired
    public FormateurRepository formateurRepository;


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

    @GetMapping(value = "/getNameById/{id}")
    public String getNameById(@PathVariable("id") String id) {
        if(etudiantRepository.findEtudiantById(id)!=null)
        {
            return  etudiantRepository.findEtudiantById(id).getNom();
        }
        else
            if(formateurRepository.findFormateurById(id)!=null)
            {
                return  formateurRepository.findFormateurById(id).getNom();
            }
            else
            {
                return "vide";
            }

    }
    //Supprimer un etudiant par cin
    @DeleteMapping(value = "/SupprimerEtudiant/{id}")
    public String deleteEtudiant(@PathVariable("id") String id) {
        etudiantRepository.deleteById(id);
        return "L'étudiant a été supprimé avec succès";
    }

    //Ajouter un étudian t 
    @PostMapping(value = "/AjoutEtudiant")
    public String addEtudiant(@RequestBody Etudiant etudiant) throws IOException, MessagingException {
        Etudiant insertedEtudiant = etudiantRepository.insert(etudiant);
        SendEmail email = new SendEmail();
        email.sendmail(insertedEtudiant.getEmail(),"Bienvenu dans TechAcademy","Votre inscription est confirmee,"+etudiant.getNom()+" " );
        return "L'étudiant a été ajouté avec succès!";
    }

    //Modifier un etudiant
    @PutMapping(value="/UpdateEtudiant/{id}")
    public Etudiant updateEtudiant(@RequestBody Etudiant etudiant, @PathVariable String id)
    {
      Etudiant etudiantData = etudiantRepository.findEtudiantById(id);
        if(etudiantData!=null) {
        	etudiantData.setCin(etudiant.getCin());
           // etudiantData.setPhoto(etudiant.getPhoto());
            if(etudiant.getMot_de_passe()!=null){ etudiantData.setMot_de_passe(etudiant.getMot_de_passe());}
            etudiantData.setNom(etudiant.getNom());
            etudiantData.setPrenom(etudiant.getPrenom());
            etudiantData.setAdresse(etudiant.getAdresse());
            etudiantData.setDate_de_naissance(etudiant.getDate_de_naissance());
            etudiantData.setNum_tel(etudiant.getNum_tel());
            etudiantRepository.save(etudiantData);
        }
        return etudiantData;
    }
    //Modifier mot de passe d un etudiant
    @PutMapping(value="/UpdatePassword/{id}")
    public String UpdatePassword(@RequestBody Password passwordEtudiant, @PathVariable String id)
    {
        Etudiant etudiantData = etudiantRepository.findEtudiantById(id);

            if(etudiantData.getMot_de_passe().equals(passwordEtudiant.getOldPassword())) {
                etudiantData.setMot_de_passe(passwordEtudiant.getNewPassword());
                etudiantRepository.save(etudiantData);
                return "L'étudiant a été modifié avec succée";
            }
            else
            {
                return "";
            }





    }


    @PutMapping(value = "/api/upload/{id}")
    public Etudiant uploadToLocalFileSystem(@RequestParam("file") MultipartFile file,@PathVariable String id) {
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

        Etudiant e = etudiantRepository.findEtudiantById(id);
        e.setPhoto(fileName);
        etudiantRepository.save(e);

        return e;
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

  /*  @GetMapping(value = "/image/{fileName:.+}")
    public @ResponseBody byte[] getImage(@PathVariable String fileName) throws IOException {
        String fileBasePath="/home/dell/Documents/upload/";
        Path path = Paths.get(fileBasePath + fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream in = resource.getInputStream();
        return IOUtils.toByteArray(in);
    }
*/
    @GetMapping(
            value = "/image/{fileName:.+}"
    )
    public  String getImageWithMediaType(@PathVariable String fileName) throws IOException {
        String fileBasePath="/home/dell/Documents/upload/";
        Path path = Paths.get(fileBasePath + fileName);
        /*Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream in = resource.getInputStream();
        return IOUtils.toByteArray(in);*/
        byte[] fileContent = FileUtils.readFileToByteArray(new File(path.toString()));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        return  encodedString;
    }


}







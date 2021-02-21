package com.techcenter.backend.controllers;


import com.techcenter.backend.models.Documents;
import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Planing;
import com.techcenter.backend.repositories.DocumentsRepository;
import com.techcenter.backend.repositories.PlaningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentsController {
    @Autowired
    DocumentsRepository documentsRepository;

    //Liste des planing by session
    @GetMapping(value = "/ListDocumentsBySession/{id}")
    public List<Documents> ListDocumentsBySession(@PathVariable("id") String id) {
        return documentsRepository.findDocumentsByIdSession(id);
    }



    @GetMapping(value = "/getDocumentById/{id}")
    public Documents getDocumentById(@PathVariable("id") String id) {
        return documentsRepository.findById(id);
    }

    @PostMapping(value ="/AjoutDocuments")
    public String AjoutDocuments(@RequestBody Documents documents) {
        try
        {
            documentsRepository.save(documents);
            return "Le Document a été ajouté avec succès!" ;
        }
        catch (Exception e)
        {
            return e.getMessage();
        }

    }

    @PostMapping (value = "/api/uploadDocuments")
    public String uploadToLocalFileSystem(@RequestParam("file") MultipartFile file) {
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

    @GetMapping("/downloadDocument/{fileName:.+}")
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

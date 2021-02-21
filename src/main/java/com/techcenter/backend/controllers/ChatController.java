package com.techcenter.backend.controllers;


import com.techcenter.backend.models.Chat;
import com.techcenter.backend.models.Documents;
import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Session;
import com.techcenter.backend.repositories.ChatRepository;
import com.techcenter.backend.repositories.DocumentsRepository;
import com.techcenter.backend.repositories.EtudiantRepository;
import com.techcenter.backend.repositories.SessionRepository;
import com.techcenter.backend.services.PushNotification;
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
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {
    PushNotification pushNotification = new PushNotification();
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    EtudiantRepository etudiantRepository;

    @GetMapping(value = "/ListMessagesBySession/{id}")
    public List<Chat> ListDocumentsBySession(@PathVariable("id") String id) {
        return chatRepository.findByIdSession(id);
    }



    @PostMapping (value = "/AjouterMessage")
    public String ajouterChat(@RequestBody Chat message) {
        chatRepository.save(message);
        String x=message.getIdSession();
        Session session = sessionRepository.findSessionById(x);
        String data="{\"id\": \""+message.getId() +"\",\"text\": \""+message.getText()+"\",\"createdAt\": \""+message.getCreatedAt()+"\",\"idUser\": \""+message.getIdUser()+"\"}";

     //   String data="{\"message\": \""+message+"\"}";
        for(String e : session.getListe_des_etudiants())
        {
            Etudiant etudiant= etudiantRepository.findEtudiantById(e);
            pushNotification.sendMessageToUser("Add message",etudiant.getDeviceId(),data);
        }
        return "bien ajouter";
    }




}

package com.techcenter.backend.controllers;


import com.techcenter.backend.models.Formateur;
import com.techcenter.backend.models.Session;
import com.techcenter.backend.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SessionController {

    @Autowired
    public SessionRepository sessionRepository;


    //Liste des sessions
    @GetMapping(value = "/ListedesSessions")
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    //Supprimer une session
    @DeleteMapping(value ="/SupprimerSession/{id}")
    public String deleteSession(@PathVariable("id")   String id)
    {
        sessionRepository.deleteSessionById(id);
        return  "La session a été supprimée avec succès";
    }

    //Supprimer tous les sessions
    @DeleteMapping(value ="/SupprimerSessions")
    public String deleteSessions()
    {
        sessionRepository.deleteAll();
        return  "ddddddddd";
    }

    //Ajouter une session
    @PostMapping(value ="/AjoutSession")
    public String addFormateur(@RequestBody Session session) {

        Session insertedSession = sessionRepository.insert(session);
        return "La session a été ajoutée avec succès!" ;
    }
    //Modifier une session
    @PutMapping(value="/UpdateSession/{id}")
    public String updateSession(@RequestBody Session session, @PathVariable String id)
    {
        Optional<Session> sessionData = sessionRepository.findSessionById(id);
        if(sessionData.isPresent()) {
            Session s = sessionData.get();
            s.setNom_du_session(session.getNom_du_session());
            s.setDate_de_début(session.getDate_de_début());
            s.setDate_de_fin(session.getDate_de_fin());
            s.setNb_places(session.getNb_places());
            sessionRepository.save(s);
        }
        return "La session a été modifié avec succée";
    }



}

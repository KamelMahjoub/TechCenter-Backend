package com.techcenter.backend.controllers;


import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Formation;
import com.techcenter.backend.models.Session;
import com.techcenter.backend.repositories.EtudiantRepository;
import com.techcenter.backend.repositories.FormationRepository;
import com.techcenter.backend.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SessionController {


    @Autowired
    public FormationRepository formationRepository;
    @Autowired
    public SessionRepository sessionRepository;
    @Autowired
    public EtudiantRepository etudiantRepository;


    //Liste des sessions
    @GetMapping(value = "/ListedesSessions")
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }
    //Liste des sessions par etudiant
    @GetMapping(value = "/getSessionByIdEtudiant/{id}")
    public List<Session> getSessionByIdEtudiant(@PathVariable("id") String id) {
       List<Session>sessionList=  sessionRepository.findAll();
       List<Session> sessionsEtudiant = new ArrayList<>();
       for(Session s :sessionList)
       {
           if(s.getListe_des_etudiants().contains(id))
           {
               sessionsEtudiant.add(s);
           }
       }
        return sessionsEtudiant;
    }

    @GetMapping(value = "/getSessionByIdFormation/{id}")
    public List<Session> getSessionByIdFormation(@PathVariable("id") String id) {
        List<Session>sessionList=  sessionRepository.findAll();
        List<Session> sessionsFormation= new ArrayList<>();
        for(Session s :sessionList)
        {
            if(s.getIdFormation().equals(id))
            {
                sessionsFormation.add(s);
            }
        }
        return sessionsFormation;
    }


    @GetMapping(value = "/getSessionById/{id}")
    public Session getSessionById(@PathVariable("id") String id) {

        return sessionRepository.findSessionById(id);
    }





    //Supprimer une session
    @DeleteMapping(value ="/SupprimerSession/{id}}")
    public String deleteSession(@PathVariable("id")   String id)
    {
     sessionRepository.deleteById(id);
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
    public String addSession(@RequestBody Session session) {
    	sessionRepository.save(session);
        return "La session a été ajoutée avec succès!" ;
    }
    //Modifier une session
    @PutMapping(value="/UpdateSession/{id}}")
    public String updateSession(@RequestBody Session session, @PathVariable String id)
    {
    	Session s = sessionRepository.findSessionById(id);
        if(s!=null) {

            s.setNom_du_session(session.getNom_du_session());
            s.setDate_de_début(session.getDate_de_début());
            s.setDate_de_fin(session.getDate_de_fin());
            s.setNb_places(session.getNb_places());
        	sessionRepository.save(s);
        }
        return "La session a été modifié avec succée";
    }


    //Inscription etudiant
    @PutMapping(value="/InscriptionEtudiant/{idsession}/{idetudiant}")
    public String InscriptionEtudiant(@PathVariable String idsession , @PathVariable String idetudiant)
    {
      List<String> listIdEtudiant = new ArrayList<>();
        Session s = sessionRepository.findSessionById(idsession);
        if(s.getNb_inscrits()==s.getNb_places())
            return "la session a déja le nombre maximum d'inscriptions";
        else
        {
            listIdEtudiant=  s.getListe_des_etudiants();
            if(listIdEtudiant!=null)
            {
                listIdEtudiant.add(idetudiant);
            }
            else
            {
                listIdEtudiant= new ArrayList<>();
                listIdEtudiant.add(idetudiant);
            }
            s.setListe_des_etudiants(listIdEtudiant);
            s.setNb_inscrits(s.getNb_inscrits()+1);
            sessionRepository.save(s);

            return "Inscription réussite";
        }



    }


}

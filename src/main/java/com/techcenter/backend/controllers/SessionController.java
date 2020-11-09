package com.techcenter.backend.controllers;


import com.techcenter.backend.models.Formateur;
import com.techcenter.backend.models.Formation;
import com.techcenter.backend.models.Session;
import com.techcenter.backend.repositories.FormationRepository;
import com.techcenter.backend.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SessionController {

    @Autowired
    public SessionRepository sessionRepository;
    @Autowired
    public FormationRepository formationRepository;

    //Liste des sessions
    @GetMapping(value = "/ListedesSessions")
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }
    
    @GetMapping(value = "/getSessionById/{id}/{id2}")
    public Session getSessionById(@PathVariable("id") String id,@PathVariable("id2") int id2) {
    Formation formation=	formationRepository.findFormationById(id);
    
        return formation.getListeDesSession().get(id2);
    }
    
    @GetMapping(value = "/getSessionByIdFormation/{id}")
    public List<Session> getSessionByIdFormation(@PathVariable("id") String id) {
    Formation formation=	formationRepository.findFormationById(id);
    
        return formation.getListeDesSession();
    }
    
    @GetMapping(value = "/getSessionByIdFormateur/{id}")
    public List<Session> getSessionByIdFormateur(@PathVariable("id") String id) {
    	
    	try {
    		   return formationRepository.findFormationById(id).getListeDesSession();
    	}
    	catch(NullPointerException nullPointerException)
    	{
    		return null;
    	}
    	
   
    }
    /*
    @GetMapping(value = "/getSessionByIdEtudiant/{id}")
    public List<Session> getSessionByIdEtudiant(@PathVariable("id") String id) {
        return sessionRepository.findSessionByIdEtudiant(id);
    }
*/
    //Supprimer une session
    @DeleteMapping(value ="/SupprimerSession/{id}/{index}")
    public String deleteSession(@PathVariable("id")   String id,@PathVariable("index")   int id2)
    {
    	Formation formation=formationRepository.findFormationById(id);
    	formation.getListeDesSession().remove(id2);
    	formationRepository.save(formation);
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
    @PutMapping(value ="/AjoutSession/{id}")
    public String addSession(@RequestBody Session session,@PathVariable String id) {
    	 
    	
    	Session s=sessionRepository.save(session);
    	
    	Formation formation=   formationRepository.findFormationById(id);
         if(formation!=null)
         {
        	 List<Session> listeSessions = formation.getListeDesSession();
        	 if(listeSessions!=null)
        	 {
        		 listeSessions.add(s);
        	 }
        	 else
        	 {
        		  listeSessions = new ArrayList<Session>();
        		
        		  listeSessions.add(s);
        		 
        	 }
        	
        	 formation.setListeDesSession(listeSessions);
        	 formationRepository.save(formation);
         }
      //  Session insertedSession = sessionRepository.insert(session);
        return "La session a été ajoutée avec succès!" ;
    }
    //Modifier une session
    @PutMapping(value="/UpdateSession/{id}/{id2}")
    public String updateSession(@RequestBody Session session, @PathVariable String id, @PathVariable int id2)
    {
    	Formation formation= formationRepository.findFormationById(id);
    	
     Session sessionData = formation.getListeDesSession().get(id2);
        if(sessionData!=null) {
           
        	sessionData.setNom_du_session(session.getNom_du_session());
        	sessionData.setDate_de_début(session.getDate_de_début());
        	sessionData.setDate_de_fin(session.getDate_de_fin());
        	sessionData.setNb_places(session.getNb_places());
        	formationRepository.save(formation);
        }
        return "La session a été modifié avec succée";
    }



}

package com.techcenter.backend.controllers;

import com.techcenter.backend.models.*;
import com.techcenter.backend.repositories.FormateurRepository;
import com.techcenter.backend.repositories.FormationRepository;

import com.techcenter.backend.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FormateurController {

    @Autowired
    public FormateurRepository formateurRepository;
    @Autowired
    public FormationRepository formationRepository;
    @Autowired
    public SessionRepository sessionRepository;

    //Liste des formateurs
    @GetMapping(value = "/ListedesFormateurs")
    public List<Formateur> getAllFormateurs() {
        return formateurRepository.findAll();
    }

    //Supprimer un formateur par cin
    @DeleteMapping(value ="/SupprimerFormateur/{cin}")
    public String deleteFormateur(@PathVariable("cin")   String cin)
    {
        formateurRepository.deleteFormateurByCin(cin);
        return  "Le formateur a été supprimé avec succès";
    }

    //Supprimer tous les formateurs
    @DeleteMapping(value ="/SupprimerFormateurs")
    public String deleteFormateurs()
    {
        formateurRepository.deleteAll();
        return  "ddddddddd";
    }

    //Ajouter un formateur
    @PostMapping(value ="/AjoutFormateur")
    public String addFormateur(@RequestBody Formateur formateur) {

        Formateur insertedFormateur = formateurRepository.insert(formateur);

        return "Le formateur a été ajouté avec succès!" ;
    }
//Modifier un formateur
    @PutMapping(value="/UpdateFormateur/{id}")
    public Formateur updateFormateur(@RequestBody Formateur formateur, @PathVariable String id)
    {
        Formateur formateurData = formateurRepository.findFormateurById(id);
       if(formateurData!=null) {
           formateurData.setCin(formateur.getCin());
           formateurData.setMot_de_passe(formateur.getMot_de_passe());
           formateurData.setNom(formateur.getNom());
           formateurData.setPrenom(formateur.getPrenom());
           formateurData.setAdresse(formateur.getAdresse());
           formateurData.setDate_de_naissance(formateur.getDate_de_naissance());
           formateurData.setNum_tel(formateur.getNum_tel());
           formateurRepository.save(formateurData);
       }
        return formateurData;
    }
    @PutMapping(value="/UpdatePasswordFormateur/{id}")
    public String UpdatePassword(@RequestBody Password passwordEtudiant, @PathVariable String id)
    {
        Formateur formateurData = formateurRepository.findFormateurById(id);

        if(formateurData.getMot_de_passe().equals(passwordEtudiant.getOldPassword())) {
            formateurData.setMot_de_passe(passwordEtudiant.getNewPassword());
            formateurRepository.save(formateurData);
            return "Le formateur a été modifié avec succée";
        }
        else
        {
            return "";
        }





    }



    @GetMapping(value = "/getFormateurByCin/{id}")
    public Formateur getFormateurtByCin(@PathVariable("id") String id) {
        return formateurRepository.findFormateurById(id);
    }
    @PostMapping(value ="/AffecterFomateur")
    public String AffecterFomateur(@RequestBody Affecter affecter) {

        Session sessionFormateur=sessionRepository.findSessionById(affecter.getIdSession());
        List<String> listeSessions =    sessionFormateur.getListe_de_formateurs();

    	 if(listeSessions!=null)
    	 {
    		 listeSessions.add(affecter.getIdFormateur());
    	 }
    	 else
    	 {
    		  listeSessions = new ArrayList<String>();
    		
    		  listeSessions.add(affecter.getIdFormateur());
    		 
    	 }

        sessionFormateur.setListe_de_formateurs(listeSessions);
    	 sessionRepository.save(sessionFormateur);
    	
    //    Formateur insertedFormateur = formateurRepository.insert(formateur);
        return "Le Session a été affecté avec succès!" ;
    }


    //Liste des sessions par cin
    @GetMapping(value = "/getListeSessionsFormateurs/{id}")
    public List<Session> getListeSessionsFormateurs(@PathVariable("id") String id) {
        List<Session> sessionList = sessionRepository.findAll();
        List<Session> sessionFormateur = new ArrayList<>();
        for(Session s : sessionList)
        {
         if(s.getListe_de_formateurs().contains(id))
         {
             sessionFormateur.add(s);
         }
        }
        return sessionFormateur;
    }

    //Liste des sessions par cin
    @GetMapping(value = "/getListFormation/{id}")
    public List<Formation> getListFormation(@PathVariable("id") String id) {
        List<Formation> formationList = new ArrayList<>();
        List<Session> sessionList = sessionRepository.findAll();
        for(Session s : sessionList)
        {
            if(s.getListe_de_formateurs().contains(id))
            {
                Formation f=formationRepository.findFormationById(s.getIdFormation());
                if(!formationList.contains(f)){ formationList.add(f);}
            }
        }
        return formationList;
    }
    @GetMapping(value = "/getListSessionById/{id}/{id2}")
    public List<Session> getListSessionById(@PathVariable("id") String id,@PathVariable("id2") String id2) {

        List<Session> sessionList = sessionRepository.findSessionByidFormation(id);
        List<Session> sessionListRe = new ArrayList<>();
        for(Session s : sessionList)
        {
            if(s.getListe_de_formateurs().contains(id2))
            {
                sessionListRe.add(s);
            }
        }
        return sessionListRe;
    }

    @GetMapping(value = "/getFormateurByEmail/{email}")
    public Formateur getFormateurByEmail(@PathVariable("email") String email) {
        return formateurRepository.getFormateurByEmail(email);
    }


}

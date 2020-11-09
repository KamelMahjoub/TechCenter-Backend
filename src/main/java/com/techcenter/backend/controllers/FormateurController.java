package com.techcenter.backend.controllers;

import com.techcenter.backend.models.Affecter;
import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Formateur;
import com.techcenter.backend.models.Session;
import com.techcenter.backend.repositories.FormateurRepository;
import com.techcenter.backend.repositories.FormationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FormateurController {

    @Autowired
    public FormateurRepository formateurRepository;
    

    @Autowired
    public FormationRepository formationRepository;



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
    @PutMapping(value="/UpdateFormateur/{cin}")
    public String updateFormateur(@RequestBody Formateur formateur, @PathVariable String cin)
    {
        Formateur formateurData = formateurRepository.findFormateurByCin(cin);
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
        return "Le formateur a été modifié avec succée";
    }

    @GetMapping(value = "/getFormateurByCin/{cin}")
    public Formateur getFormateurtByCin(@PathVariable("cin") String cin) {
        return formateurRepository.findFormateurByCin(cin);
    }
    @PostMapping(value ="/AffecterFomateur")
    public String AffecterFomateur(@RequestBody Affecter affecter) {

    	Formateur formateur=formateurRepository.findFormateurByCin(affecter.getNomFormateur());
    	Session session=formationRepository.findFormationById(affecter.getTitreFormation()).getListeDesSession().get(affecter.getSaisonFormation());
    	
    	 List<Session> listeSessions = formateur.getListeDesSessions();
    	 if(listeSessions!=null)
    	 {
    		 listeSessions.add(session);
    	 }
    	 else
    	 {
    		  listeSessions = new ArrayList<Session>();
    		
    		  listeSessions.add(session);
    		 
    	 }
    	
    	 formateur.setListeDesSessions(listeSessions);
    	 formateurRepository.save(formateur);
    	
    //    Formateur insertedFormateur = formateurRepository.insert(formateur);
        return "Le formateur a été ajouté avec succès!" ;
    }
   


}

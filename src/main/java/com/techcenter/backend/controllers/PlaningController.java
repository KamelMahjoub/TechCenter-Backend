package com.techcenter.backend.controllers;


import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Formation;
import com.techcenter.backend.models.Planing;
import com.techcenter.backend.models.Session;
import com.techcenter.backend.repositories.EtudiantRepository;
import com.techcenter.backend.repositories.FormationRepository;
import com.techcenter.backend.repositories.PlaningRepository;
import com.techcenter.backend.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PlaningController {
    @Autowired
    PlaningRepository planingRepository;

    //Liste des planing by session
    @GetMapping(value = "/ListPlaningBySession/{id}")
    public List<Planing> listPlaningBySession(@PathVariable("id") String id) {
        return planingRepository.findByIdSession(id);
    }


    @GetMapping(value = "/getPlaningById/{id}")
    public Planing getPlaningById(@PathVariable("id") String id) {
        return planingRepository.findById(id);
    }

    @PostMapping(value ="/AjoutPlaning")
    public String AjoutPlaning(@RequestBody Planing planing) {
        try
        {
            planingRepository.save(planing);
            return "Le planing a été ajouté avec succès!" ;
        }
        catch (Exception e)
        {
            return e.getMessage();
        }

    }

    @PutMapping(value ="/UpdatePlaning/{id}")
    public String UpdatePlaning(@RequestBody Planing planing,@PathVariable String id) {
        try
        {
         Planing p= planingRepository.findById(id);
           if(p!=null)
           {
               p.setTitre(planing.getTitre());
               p.setFinish(planing.isFinish());
             planingRepository.save(p);
           }
            return "Le planing a été modifier avec succès!" ;
        }
        catch (Exception e)
        {
            return e.getMessage();
        }

    }



    //Supprimer une session
    @DeleteMapping(value ="/SupprimerPlaning/{id}")
    public String deleteSession(@PathVariable("id")   String id)
    {
       planingRepository.deleteById(id);
        return  "Le planing a été supprimée avec succès";

    }


}

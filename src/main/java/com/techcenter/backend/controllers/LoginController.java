package com.techcenter.backend.controllers;

import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Formateur;
import com.techcenter.backend.repositories.EtudiantRepository;
import com.techcenter.backend.repositories.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    public EtudiantRepository etudiantRepository;
    @Autowired
    public FormateurRepository formateurRepository;

    @GetMapping(value = "/login/{email}/{mot_de_passe}")
    public Object login(@PathVariable("email") String email,@PathVariable("mot_de_passe") String mot_de_passe) {
        Etudiant e ;
        Formateur f;

        e = etudiantRepository.getEtudiantByEmailAndMotdepasse(email,mot_de_passe);
        if(e==null)
        {
            f=formateurRepository.getFormateurByEmailAndMotdepasse(email,mot_de_passe);
            if(f==null)
            {
                return null;
            }
            else
            {
                f = formateurRepository.getFormateurByEmail(email);
                return f;
            }

        }
        else
        {
            e = etudiantRepository.findEtudiantByEmail(email);
            return e;
        }
    }
}

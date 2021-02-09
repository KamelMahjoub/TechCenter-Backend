package com.techcenter.backend.repositories;

import com.techcenter.backend.models.Formateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormateurRepository extends MongoRepository<Formateur,Long> {

    String deleteFormateurByCin(String cin);
    Formateur findFormateurById (String id);
    Formateur getFormateurByEmailAndMotdepasse(String email,String mot_de_passe);
    Formateur getFormateurByEmail(String email);

}

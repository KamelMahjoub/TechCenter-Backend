package com.techcenter.backend.repositories;

import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Formateur;
import com.techcenter.backend.models.Formation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends MongoRepository<Etudiant,Long> {

    String deleteEtudiantByCin(String cin);
    Etudiant findEtudiantByCin (String cin);


}

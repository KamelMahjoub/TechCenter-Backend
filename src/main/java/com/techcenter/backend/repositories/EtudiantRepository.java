package com.techcenter.backend.repositories;

import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Formateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends MongoRepository<Etudiant,Long> {

    String deleteEtudiantByCin(String cin);
    Optional<Etudiant> findEtudiantByCin (String cin);

}

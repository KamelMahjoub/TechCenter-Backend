package com.techcenter.backend.repositories;

import com.techcenter.backend.models.Formateur;
import com.techcenter.backend.models.Formation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FormationRepository extends MongoRepository<Formation,Long> {

    String deleteFormationsById(String id);
   Formation findFormationById(String id);
   List<Formation> findFormationsByTitre(String titre);
    List<Formation> findAllByTitreContaining(String titre);



}

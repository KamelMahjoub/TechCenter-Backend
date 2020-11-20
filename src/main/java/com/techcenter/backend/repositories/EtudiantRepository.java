package com.techcenter.backend.repositories;

import com.techcenter.backend.models.Etudiant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EtudiantRepository extends MongoRepository<Etudiant,Long> {

    String deleteEtudiantByCin(String cin);
    Etudiant findEtudiantByCin (String cin);
    Etudiant findEtudiantByEmail(String email);
    Etudiant  getEtudiantByEmailAndMotdepasse(String email,String mot_de_passe);


}

package com.techcenter.backend.repositories;

import com.techcenter.backend.models.Etudiant;
import com.techcenter.backend.models.Formation;
import com.techcenter.backend.models.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface SessionRepository extends MongoRepository<Session,Long> {

    String deleteSessionById(String id);
    Session findSessionById(String id);
   /* List<Session> findSessionByIdFormateur(String id);
    List<Session> findSessionByIdEtudiant(String id);*/
}

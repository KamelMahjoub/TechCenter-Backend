package com.techcenter.backend.repositories;

import com.techcenter.backend.models.Documents;
import com.techcenter.backend.models.Etudiant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DocumentsRepository extends MongoRepository<Documents,Long> {


    List<Documents> findDocumentsByIdSession (String id);
     Documents findById (String id);
}

package com.techcenter.backend.repositories;



import com.techcenter.backend.models.Planing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PlaningRepository extends MongoRepository<Planing,Long> {

    List<Planing> findByIdSession(String id);
    Planing findById(String id);
    boolean deleteById(String  id);

}

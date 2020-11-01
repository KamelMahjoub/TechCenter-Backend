package com.techcenter.backend.repositories;


import com.techcenter.backend.models.Spécialité;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpécialitéRepository extends MongoRepository<Spécialité,Long> {

    Optional<Spécialité> findSpécialitéById (String cin);
    String deleteSpécialitéById(String id);


}

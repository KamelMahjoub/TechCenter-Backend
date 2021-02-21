package com.techcenter.backend.repositories;

import com.techcenter.backend.models.Chat;
import com.techcenter.backend.models.Documents;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ChatRepository extends MongoRepository<Chat,Long> {
    List<Chat> findByIdSession (String id);

}

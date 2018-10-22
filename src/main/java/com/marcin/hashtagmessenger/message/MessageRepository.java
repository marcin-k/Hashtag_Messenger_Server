package com.marcin.hashtagmessenger.message;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;

@Service
public interface MessageRepository extends CrudRepository<Message, Long> {
}

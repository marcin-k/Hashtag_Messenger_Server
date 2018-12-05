package com.marcin.hashtagmessenger.message;
/************************************************************
 * Interface used to expose the Create, Read, Update and
 * Delete methods on the object
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;

@Service
public interface MessageRepository extends CrudRepository<Message, Long> {
}

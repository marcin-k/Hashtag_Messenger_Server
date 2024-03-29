package com.marcin.hashtagmessenger.parentUser;

/************************************************************
 * Interface used to expose the Create, Read, Update and
 * Delete methods on the object
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface ParentUserRepository extends CrudRepository<ParentUser, Long> {
}

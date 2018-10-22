package com.marcin.hashtagmessenger.parentUser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface ParentUserRepository extends CrudRepository<ParentUser, Long> {
}

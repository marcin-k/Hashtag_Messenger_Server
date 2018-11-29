package com.marcin.hashtagmessenger.approval;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface NewContactRequestRepository extends CrudRepository<NewContactRequest, Long>{
}

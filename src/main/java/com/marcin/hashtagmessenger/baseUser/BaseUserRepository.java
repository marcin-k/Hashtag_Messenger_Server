package com.marcin.hashtagmessenger.baseUser;
/************************************************************
 * Interface used to expose the Create, Read, Update and
 * Delete methods on the object
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface BaseUserRepository extends CrudRepository<BaseUser, Long> {
    BaseUser findByUsername(String username);
}

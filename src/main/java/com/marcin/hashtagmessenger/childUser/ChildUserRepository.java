package com.marcin.hashtagmessenger.childUser;
/************************************************************
 * Interface used to expose the Create, Read, Update and
 * Delete methods on the object
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
import com.marcin.hashtagmessenger.childUser.ChildUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

//@RepositoryRestResource(exported = false)
@Service
public interface ChildUserRepository extends CrudRepository<ChildUser, Long> {
}

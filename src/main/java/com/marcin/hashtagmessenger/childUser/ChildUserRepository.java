package com.marcin.hashtagmessenger.childUser;

import com.marcin.hashtagmessenger.childUser.ChildUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

//@RepositoryRestResource(exported = false)
@Service
public interface ChildUserRepository extends CrudRepository<ChildUser, Long> {
}

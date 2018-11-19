package com.marcin.hashtagmessenger.core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface BaseUserRepository extends CrudRepository<BaseUser, Long> {
    BaseUser findByUsername(String username);
}

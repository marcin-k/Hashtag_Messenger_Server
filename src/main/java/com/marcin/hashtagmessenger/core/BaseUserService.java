package com.marcin.hashtagmessenger.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseUserService {

    @Autowired
    BaseUserRepository baseUserRepository;

    //adds a new contact to user with userId
    public BaseUser createContact(Long contactId, Long userId){
        BaseUser user = baseUserRepository.findById(userId).get();
        BaseUser contact = baseUserRepository.findById(contactId).get();
        user.addNewContact(contact);
        return baseUserRepository.save(user);
    }

    //returns list of contacts for a user with id
    public List<BaseUser> read(Long id){
        BaseUser baseUser = baseUserRepository.findById(id).get();
        if (baseUser!=null) {
            return baseUser.getContacts();
        }
        return new ArrayList<>();
    }
}

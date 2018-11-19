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

    //returns password set or password not set if user not found
    public String setPassword(Long id, String password){
        BaseUser bs = baseUserRepository.findById(id).get();
        bs.setPassword(password);
        baseUserRepository.save(bs);
        return "password set";
    }

    //return an id of user if username and password are correct
    public Long login(String username, String password){
        BaseUser bs = baseUserRepository.findByUsername(username);
        if (bs.getPassword().equals(password)){
            return bs.getId();
        }
        return 0l;
    }
}

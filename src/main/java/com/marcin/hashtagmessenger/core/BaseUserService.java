package com.marcin.hashtagmessenger.core;

import com.marcin.hashtagmessenger.childUser.ChildUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseUserService {

    @Autowired
    BaseUserRepository baseUserRepository;

    //adds a new contact to user with userId
    public String createContact(Long contactId, Long userId){
        BaseUser user = baseUserRepository.findById(userId).get();
        BaseUser contact = baseUserRepository.findById(contactId).get();
        if (!user.getContacts().contains(contact)){
            user.addNewContact(contact);
            baseUserRepository.save(user);
            return "added";
        }
        else{
            return "already in your contact list";
        }
    }

    //returns list of contacts for a user with id
    // uses a new temp array to overcome the issue of implementing bidirectional
    // referencing in parent users have many kids / child has many parents
    public List<BaseUser> read(Long id){
        BaseUser baseUser = baseUserRepository.findById(id).get();
        if (baseUser!=null) {
            ArrayList<BaseUser> tempArray = new ArrayList<>();
            for (BaseUser b:baseUser.getContacts()){

                tempArray.add(new BaseUser(b.getId(),b.getUsername(), b.getFirstName(), b.getLastName(), "",
                        b.getMessages(), b.getContacts()));
            }
            return tempArray;
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

    //searches the users to find matching ones
    public List<BaseUser> search(String username){
        Iterable<BaseUser> iter = baseUserRepository.findAll();
        List<BaseUser> allUsers = new ArrayList<>();
        iter.forEach(allUsers::add);
        List<BaseUser> newList = new ArrayList<>();
        for (BaseUser b : allUsers){
//            System.out.println(b.getLastName());
            if((b.getUsername().toLowerCase().contains(username.toLowerCase())) ||
                    (b.getFirstName().toLowerCase().contains(username.toLowerCase()))||
                    (b.getLastName().toLowerCase().contains(username.toLowerCase()))){
                if (b instanceof ChildUser){
                    if (((ChildUser) b).isCanBeFound()){
                        newList.add(new BaseUser(b.getId(),b.getUsername(), b.getFirstName(), b.getLastName(), "",
                                b.getMessages(), b.getContacts()));
                    }
                }
                else {
                    newList.add(new BaseUser(b.getId(),b.getUsername(), b.getFirstName(), b.getLastName(), "",
                            b.getMessages(), b.getContacts()));
                }
//                newList.add(b);
            }
        }
        return newList;
    }
}

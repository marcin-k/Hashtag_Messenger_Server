package com.marcin.hashtagmessenger.parentUser;
/************************************************************
 * Service class used to define the implementation of the methods
 * invoked in the webservice
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
import com.marcin.hashtagmessenger.childUser.ChildUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParentUserService {

    @Autowired
    ParentUserRepository parentUserRepository;

    //used to create a new parent user object
    public ParentUser createParentUser(ParentUser parentUser){
        return parentUserRepository.save(parentUser);
    }

    //used to return list of children for a parent with a given id
    public List<ChildUser> read(Long id){
        ParentUser parentUser = parentUserRepository.findById(id).get();
        if (parentUser!=null) {
            Iterable<ChildUser> iter = parentUser.getChildren();
            List<ChildUser> allUsers = new ArrayList<>();
            iter.forEach(allUsers::add);
            List<ChildUser> newList = new ArrayList<>();
            for (ChildUser c : allUsers){
                    newList.add(new ChildUser(c.getId(), c.getUsername(), c.getFirstName(), c.getLastName(),
                            "", c.getParents(), c.getRequests(), c.isCanAddNewContacts(), c.isCanBeFound(),
                            c.isCanReceiveMessageFromNonConctact(), c.getDailyAllowance()));
            }
            return newList;
        }
        return new ArrayList<>();
    }
}

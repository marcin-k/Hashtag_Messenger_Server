package com.marcin.hashtagmessenger.childUser;

import com.marcin.hashtagmessenger.parentUser.ParentUser;
import com.marcin.hashtagmessenger.parentUser.ParentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChildUserService {

    @Autowired
    ChildUserRepository childUserRepository;

    @Autowired
    ParentUserRepository parentUserRepository;

    public ChildUser createChildUser(ChildUser childUser, Long parentId){
        ParentUser parentUser = parentUserRepository.findById(parentId).get();
        parentUser.addNewChild(childUser);
        return childUserRepository.save(childUser);
    }

    public ChildUser update(ChildUser childUser, Long childId){
        ChildUser child = childUserRepository.findById(childId).get();
        child.setCanAddNewContacts(childUser.isCanAddNewContacts());
        //Todo: decide if parent can update those attributes
//        child.setFirstName(childUser.getFirstName());
//        child.setLastName(childUser.getLastName());
//        child.setUserName(childUser.getUserName());


        child.setCanBeFound(childUser.isCanBeFound());
        child.setCanReceiveMessageFromNonConctact(childUser.isCanReceiveMessageFromNonConctact());
        return childUserRepository.save(child);
    }
}

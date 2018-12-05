package com.marcin.hashtagmessenger.childUser;
/************************************************************
 * Service class used to define the implementation of the methods
 * invoked in the webservice
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
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

    //creates a new child user object and adds it to parents list of children
    //add a parent to list of parents for newly created child
    public Long createChildUser(ChildUser childUser, Long parentId){
        ChildUser c = childUserRepository.save(childUser);
        ParentUser parentUser = parentUserRepository.findById(parentId).get();
        parentUser.addNewChild(c);
        parentUserRepository.save(parentUser);
        addParent(c.getId(), parentId);
        return c.getId();
    }

    //used to add parent for a child
    public ChildUser addParent(Long childId, Long parentId){
        ChildUser c = childUserRepository.findById(childId).get();
        c.addNewParent(parentUserRepository.findById(parentId).get());
        return childUserRepository.save(c);
    }

    //used to update the childs profile (permissions)
    public String update(ChildUser childUser, Long childId){
        ChildUser child = childUserRepository.findById(childId).get();
        child.setCanAddNewContacts(childUser.isCanAddNewContacts());
        //Those are excluded from possibility of being updated
//        child.setFirstName(childUser.getFirstName());
//        child.setLastName(childUser.getLastName());
//        child.setUserName(childUser.getUserName());
        child.setCanBeFound(childUser.isCanBeFound());
        child.setCanReceiveMessageFromNonConctact(childUser.isCanReceiveMessageFromNonConctact());
        child.setDailyAllowance(childUser.getDailyAllowance());
        childUserRepository.save(child);
        return "updated";
    }

    //returns the daily time allowance for child with id (in minutes)
    public int getDailyAllowance(Long id){
        ChildUser child = childUserRepository.findById(id).get();
        return child.getDailyAllowance();
    }

    //updates the daily time allowance for child with id
    public ChildUser setLimit(Long id, int limit){
        ChildUser child = childUserRepository.findById(id).get();
        child.setDailyAllowance(limit);
        return childUserRepository.save(child);
    }

    //returns a child object based on the id provided
    public ChildUser getChild(Long id){
        return childUserRepository.findById(id).get();
    }
}

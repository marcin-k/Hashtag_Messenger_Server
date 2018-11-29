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

    public Long createChildUser(ChildUser childUser, Long parentId){
        ChildUser c = childUserRepository.save(childUser);
        ParentUser parentUser = parentUserRepository.findById(parentId).get();
        parentUser.addNewChild(c);
        parentUserRepository.save(parentUser);
        addParent(c.getId(), parentId);
        return c.getId();
    }

    public ChildUser addParent(Long childId, Long parentId){
        ChildUser c = childUserRepository.findById(childId).get();
        c.addNewParent(parentUserRepository.findById(parentId).get());
        return childUserRepository.save(c);
    }


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

    public int getDailyAllowance(Long id){
        ChildUser child = childUserRepository.findById(id).get();
        return child.getDailyAllowance();
    }

    public ChildUser setLimit(Long id, int limit){
        ChildUser child = childUserRepository.findById(id).get();
        child.setDailyAllowance(limit);
        return childUserRepository.save(child);
    }

    public ChildUser getChild(Long id){
        return childUserRepository.findById(id).get();
    }
}

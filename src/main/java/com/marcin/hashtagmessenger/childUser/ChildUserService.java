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
}

package com.marcin.hashtagmessenger.parentUser;

import com.marcin.hashtagmessenger.childUser.ChildUser;
import com.marcin.hashtagmessenger.childUser.ChildUserRepository;
import com.marcin.hashtagmessenger.core.BaseUser;
import com.marcin.hashtagmessenger.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParentUserService {

    @Autowired
    ParentUserRepository parentUserRepository;

    public ParentUser createParentUser(ParentUser parentUser){
        return parentUserRepository.save(parentUser);
    }

    public List<ChildUser> read(Long id){
        ParentUser parentUser = parentUserRepository.findById(id).get();
        if (parentUser!=null) {
            return parentUser.getChildren();
        }
        return new ArrayList<>();
    }
}

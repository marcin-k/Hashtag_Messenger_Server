package com.marcin.hashtagmessenger.message;

import com.marcin.hashtagmessenger.core.BaseUser;
import com.marcin.hashtagmessenger.core.BaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    BaseUserRepository baseUserRepository;

    public Message createMessage(Message msg){
        Message m = messageRepository.save(msg);
        System.out.println(m.getId());
        BaseUser user = baseUserRepository.findById(msg.getSenderId()).get();
        user.addNewMessage(m);
        BaseUser recipient = baseUserRepository.findById(msg.getRecipientId()).get();
        recipient.addNewMessage(m);
        baseUserRepository.save(user);
        baseUserRepository.save(recipient);
        return m;
    }

    public List<Message> read(Long id){
        BaseUser baseUser = baseUserRepository.findById(id).get();
        if (baseUser!=null) {
            return baseUser.getMessages();
        }
        return new ArrayList<>();
    }

}

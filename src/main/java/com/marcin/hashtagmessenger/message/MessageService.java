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
        BaseUser user = baseUserRepository.findById(msg.getSenderId()).get();
        user.addNewMessage(msg);
        BaseUser recipient = baseUserRepository.findById(msg.getRecipientId()).get();
        recipient.addNewMessage(msg);
        return messageRepository.save(msg);
    }

    public List<Message> read(Long id){
        BaseUser baseUser = baseUserRepository.findById(id).get();
        if (baseUser!=null) {
            return baseUser.getMessages();
        }
        return new ArrayList<>();
    }

}

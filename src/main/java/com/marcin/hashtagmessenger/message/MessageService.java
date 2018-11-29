package com.marcin.hashtagmessenger.message;

import com.marcin.hashtagmessenger.pythonServerConnection.Connector;
import com.marcin.hashtagmessenger.badWordsDictionary.Dictionary;
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
        //updates message
        m.setApprovedForChild(checkMessage(msg.getBody()));
        messageRepository.save(m);

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

    //checks message content return true if appropriate for children
    private boolean checkMessage(String word){
        String[] splited = word.split("\\s+");
        for (String s : splited){
            String reply = "";
            reply = Connector.getInstance().checkWord(s);
            if (reply.equals("bad")){
                return false;
            }
            if (Dictionary.getInstance().checkWordIsBad(s)){
                return false;
            }
        }
        return true;
    }
}

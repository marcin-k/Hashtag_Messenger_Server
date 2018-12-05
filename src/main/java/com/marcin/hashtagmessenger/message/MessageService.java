package com.marcin.hashtagmessenger.message;
/************************************************************
 * Service class used to define the implementation of the methods
 * invoked in the webservice
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
import com.marcin.hashtagmessenger.childUser.ChildUser;
import com.marcin.hashtagmessenger.parentUser.ParentUser;
import com.marcin.hashtagmessenger.parentUser.ParentUserRepository;
import com.marcin.hashtagmessenger.pythonServerConnection.Connector;
import com.marcin.hashtagmessenger.badWordsDictionary.Dictionary;
import com.marcin.hashtagmessenger.baseUser.BaseUser;
import com.marcin.hashtagmessenger.baseUser.BaseUserRepository;
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

    @Autowired
    ParentUserRepository parentUserRepository;

    //creates a new message object and adds it senders and recipients list of messages
    //the sos messages are only added to senders (flag =1)
    public Message createMessage(Message msg){
        Message m = messageRepository.save(msg);
        //updates message
        m.setApprovedForChild(checkMessage(msg.getBody()));
        messageRepository.save(m);

        System.out.println(m.getId());
        BaseUser user = baseUserRepository.findById(msg.getSenderId()).get();
        user.addNewMessage(m);
        //Allows to ignore the sos messages, routing of those is done by search on kids
        //send messages
        if (msg.getFlag()!=1) {
            BaseUser recipient = baseUserRepository.findById(msg.getRecipientId()).get();
            recipient.addNewMessage(m);
            baseUserRepository.save(recipient);
        }
        baseUserRepository.save(user);

        return m;
    }

    //returns list of messages for a user with the given id
    public List<Message> read(Long id){
        BaseUser baseUser = baseUserRepository.findById(id).get();
        if (baseUser!=null) {
            List<Message> msgs = new ArrayList<>();
            for (Message m:baseUser.getMessages()){
                if (m.getFlag()==0){
                    if (baseUser instanceof ChildUser){
                        if (m.isApprovedForChild()){
                            msgs.add(m);
                        }
                    }
                    else {
                        msgs.add(m);
                    }
                }
            }
            return msgs;
        }
        return new ArrayList<>();
    }

    //returns list of sos alerts for all kids of a parent with id provided
    //loops though all kids, then all of their messages
    public List<Message> getSOS(Long id){
        ParentUser parentUser = parentUserRepository.findById(id).get();
        List<Message> msgs = new ArrayList<>();
        if (parentUser!=null) {
            for (ChildUser c : parentUser.getChildren()) {
                for (Message m : c.getMessages()) {
                    if (m.getFlag() == 1) {
                        msgs.add(m);
                    }
                }
            }
        }
        return msgs;
    }

    //deletes a message used to delete read sos messages
    //and inappropriate messages (deletes for sender and recipient)
    public String delete(Long id){
        Message msg = messageRepository.findById(id).get();
        BaseUser sender = baseUserRepository.findById(msg.getSenderId()).get();
        sender.getMessages().remove(msg);
        if (msg.getRecipientId()!=0l){
            BaseUser recipient = baseUserRepository.findById(msg.getRecipientId()).get();
            recipient.getMessages().remove(msg);
            baseUserRepository.save(recipient);
        }
        baseUserRepository.save(sender);
        messageRepository.delete(msg);

        return "done";
    }

    //marks a message as approved for child
    public String approve(Long id){
        Message m = messageRepository.findById(id).get();
        m.setApprovedForChild(true);
        messageRepository.save(m);
        return "approved";
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

    //returns list of messages that requires approval
    public List<Message> kidsmsgs(Long id){
        List<Message> messages = new ArrayList<>();
        ParentUser parentUser = parentUserRepository.findById(id).get();
        for (ChildUser c: parentUser.getChildren()){
            for (Message m : c.getMessages()){
                //only messages to children
                if (m.getRecipientId()==c.getId()) {
                    if (!m.isApprovedForChild()) {
                        messages.add(m);
                    }
                }
            }
        }
        return messages;
    }

}

package com.marcin.hashtagmessenger.message;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Message{

//-----------------------Class Variables--------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    private Long senderId;
    private Long recipientId;
    private String body;
    //visible to child or send to parent for check
    private boolean approvedForChild;

    //flag will be used to communicate to the server additional states
    //every message will be checked for flag and if value differ from 0
    //message will be treat as system message rather than communication
    private int flag;

//-----------------------Constructor------------------------------------------------------------------------------------
    protected Message(){
        id = null;
    }
}

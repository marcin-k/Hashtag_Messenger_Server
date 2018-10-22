package com.marcin.hashtagmessenger.message;

import com.marcin.hashtagmessenger.core.BaseEntity;
import lombok.Data;
import javax.persistence.Entity;

@Data
@Entity
public class Message extends BaseEntity {

//-----------------------Class Variables--------------------------------------------------------------------------------
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
        super();
    }
}

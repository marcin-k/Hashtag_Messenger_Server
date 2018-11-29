package com.marcin.hashtagmessenger.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcin.hashtagmessenger.message.Message;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/***********************************************************************************************************************
 *   Single Table Inheritance concept taken from:
 *   https://www.thoughts-on-java.org/complete-guide-inheritance-strategies-jpa-hibernate/
 *   Used to allow inheritance when using hibernate design pattern
 *
 *   Non Of the subclass attributes can be null!!!
 *
 **********************************************************************************************************************/
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "UserType")
public class BaseUser  {

//-----------------------Class Variables--------------------------------------------------------------------------------
    private String username;
    private String firstName;
    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    @ManyToMany
    private List<BaseUser> contacts;

    @ManyToMany
    private List<Message> messages;

    @JsonIgnore
    private String password;

    public void addNewMessage(Message message) {
        if (message == null) {
            messages = new ArrayList<>();
        }
        messages.add(message);
    }

    public void addNewContact(BaseUser contact) {
        if (contact == null) {
            contacts = new ArrayList<>();
        }
        contacts.add(contact);
    }

//-----------------------Constructor------------------------------------------------------------------------------------
    protected BaseUser(){
//        super();
        id = null;
    }

    public BaseUser(String userName, String firstName, String lastName, String password) {
        this();
        this.username = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    //This is never used to save persistent storage, just to create temporary object to return object without some
    //of the attributes
    public BaseUser(Long id,String userName, String firstName, String lastName, String password, List<Message> messages,
                    List<BaseUser> contacts) {
        this.id = id;
        this.username = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.messages = messages;
        this.contacts = contacts;
    }

}

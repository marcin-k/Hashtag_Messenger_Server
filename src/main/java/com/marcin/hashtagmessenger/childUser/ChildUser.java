package com.marcin.hashtagmessenger.childUser;


import com.marcin.hashtagmessenger.Approval.NewContactRequest;
import com.marcin.hashtagmessenger.core.BaseUser;
import com.marcin.hashtagmessenger.parentUser.ParentUser;
import lombok.Data;
import javax.persistence.*;
import java.util.List;


@Data
@Entity
@DiscriminatorValue("child")
public class ChildUser extends BaseUser {

//-----------------------Class Variables--------------------------------------------------------------------------------
    //TODO: set up many to many
    @ManyToMany
    private List<ParentUser> parents;

    @ManyToMany
    private List<NewContactRequest> requests;

    //enable to search and add new contact without parent approval
    private boolean canAddNewContacts;
    //enable to be found by other users without parent consent
    private boolean canBeFound;
    //enables child to received filtered messages from strangers (users not on child contact list)
    private boolean canReceiveMessageFromNonConctact;

//-----------------------Constructor------------------------------------------------------------------------------------
    protected ChildUser(){
        super();
    }

}

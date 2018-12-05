package com.marcin.hashtagmessenger.childUser;
/************************************************************
 * Child user model class used to define the template of a
 * child user
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.marcin.hashtagmessenger.approval.NewContactRequest;
import com.marcin.hashtagmessenger.baseUser.BaseUser;
import com.marcin.hashtagmessenger.parentUser.ParentUser;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@DiscriminatorValue("child")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "childId")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
public class ChildUser extends BaseUser {

//-----------------------Class Variables--------------------------------------------------------------------------------
    private int childId;

    @ManyToMany(fetch = FetchType.EAGER)
//    @JsonBackReference
    private List<ParentUser> parents;

    @ManyToMany
    private List<NewContactRequest> requests;

    //enable to search and add new contact without parent approval
    private boolean canAddNewContacts;
    //enable to be found by other users without parent consent
    private boolean canBeFound;
    //enables child to received filtered messages from strangers (users not on child contact list)
    private boolean canReceiveMessageFromNonConctact;
    //daily allowance in minutes
    private int dailyAllowance;

    //adds a rqst for parent user
    public void addNewRqst(NewContactRequest request) {
        if (requests == null) {
            requests = new ArrayList<>();
        }
        requests.add(request);
    }

    //adds a parent for parent user
    public void addNewParent(ParentUser parentUser) {
        if (parents == null) {
            parents = new ArrayList<>();
        }
        parents.add(parentUser);
    }

//-----------------------Constructors-----------------------------------------------------------------------------------
    protected ChildUser(){
        super();
    }

    public ChildUser(String userName, String firstName, String lastName, String password, List<ParentUser> parents,
                          List<NewContactRequest> requests, boolean canAddNewContacts, boolean canBeFound,
                          boolean canReceiveMessageFromNonConctact, int dailyAllowance) {

        super(userName, firstName, lastName, password);
        this.parents = parents;
        this.requests = requests;
        this.canAddNewContacts = canAddNewContacts;
        this.canBeFound = canBeFound;
        this.canReceiveMessageFromNonConctact = canReceiveMessageFromNonConctact;
        this.dailyAllowance = dailyAllowance;
    }

    public ChildUser(Long id,String userName, String firstName, String lastName, String password, List<ParentUser> parents,
                     List<NewContactRequest> requests, boolean canAddNewContacts, boolean canBeFound,
                     boolean canReceiveMessageFromNonConctact, int dailyAllowance) {

        super(id, userName, firstName, lastName, password);
        this.parents = parents;
        this.requests = requests;
        this.canAddNewContacts = canAddNewContacts;
        this.canBeFound = canBeFound;
        this.canReceiveMessageFromNonConctact = canReceiveMessageFromNonConctact;
        this.dailyAllowance = dailyAllowance;
    }
}

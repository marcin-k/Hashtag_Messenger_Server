package com.marcin.hashtagmessenger.Approval;

import com.marcin.hashtagmessenger.core.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
public class NewContactRequest extends BaseEntity {

//-----------------------Class Variables--------------------------------------------------------------------------------
    // child who request a new contact
    private Long childRequestingNewContactId;
    // contact to be added
    private Long contactToBeAddedId;
    // enables child to search for a contact (one time search if canAddNewContacts is set to false otherwise not
    // not applicable)
    private boolean canPerformThisSearch;
    // approval for adding contact (set automatically to true if canAddNewContacts is set to true)
    private boolean canAddNewContactRequester;
    // time stamp of that approval (set to request date if canAddNewContact is set to true)
    private Date requesterApprovalTimeStamp;
    // approval for contact to be added (set automatically to true if canBeFound is se to true)
    private boolean canBeAdded;
    // time stamp of the approval (set to request date if canBeFound is set to true)
    private Date newContactApprovalTimeStamp;

//-----------------------Constructor------------------------------------------------------------------------------------
    //invokes the parent constructor
    protected NewContactRequest(){
        super();
    }


}

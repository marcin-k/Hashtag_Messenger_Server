package com.marcin.hashtagmessenger.approval;
/************************************************************
 * NewContactRequest model class used to define the template of a
 * NewContactRequest
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class NewContactRequest {

//-----------------------Class Variables--------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

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
    private LocalDate requesterApprovalTimeStamp;
    // approval for contact to be added (set automatically to true if canBeFound is se to true)
    private boolean canBeAdded;
    // time stamp of the approval (set to request date if canBeFound is set to true)
    private LocalDate newContactApprovalTimeStamp;

//-----------------------Constructor------------------------------------------------------------------------------------
    //invokes the parent constructor
    protected NewContactRequest(){
        id = null;
    }


}

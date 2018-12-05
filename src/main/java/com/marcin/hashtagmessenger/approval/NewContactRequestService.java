package com.marcin.hashtagmessenger.approval;

import com.marcin.hashtagmessenger.childUser.ChildUser;
import com.marcin.hashtagmessenger.childUser.ChildUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/************************************************************
 * Service class used to define the implementation of the methods
 * invoked in the webservice
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
@Service
public class NewContactRequestService {

    @Autowired
    NewContactRequestRepository newContactRequestRepository;

    @Autowired
    ChildUserRepository childUserRepository;

    //add contact rqst to request sender and recipient
    public NewContactRequest createRqst(NewContactRequest newContactRequest){
        NewContactRequest rqst = newContactRequestRepository.save(newContactRequest);
        ChildUser childUser = childUserRepository.findById(newContactRequest.getChildRequestingNewContactId()).get();
        childUser.addNewRqst(rqst);
        childUserRepository.save(childUser);
        return rqst;
    }

    //approves the rqst with the rqst id
    public NewContactRequest approveRqst(Long rqstId){
        NewContactRequest rqst = newContactRequestRepository.findById(rqstId).get();
        rqst.setCanAddNewContactRequester(true);
        rqst.setRequesterApprovalTimeStamp(LocalDate.now());
        return newContactRequestRepository.save(rqst);
    }

    //returns list of requests created by a child with id
    public List<NewContactRequest> read(Long id){
        ChildUser childUser = childUserRepository.findById(id).get();
        if (childUser!=null) {
            return childUser.getRequests();
        }
        return new ArrayList<>();
    }
}

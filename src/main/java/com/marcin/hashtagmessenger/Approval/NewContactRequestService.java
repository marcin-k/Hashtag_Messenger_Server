package com.marcin.hashtagmessenger.Approval;

import com.marcin.hashtagmessenger.childUser.ChildUser;
import com.marcin.hashtagmessenger.childUser.ChildUserRepository;
import com.marcin.hashtagmessenger.core.BaseUser;
import com.marcin.hashtagmessenger.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewContactRequestService {

    @Autowired
    NewContactRequestRepository newContactRequestRepository;

    @Autowired
    ChildUserRepository childUserRepository;

    //add contact rqst to request sender and recipient
    public NewContactRequest createRqst(NewContactRequest newContactRequest){
        ChildUser childUser = childUserRepository.findById(newContactRequest.getChildRequestingNewContactId()).get();
        childUser.addNewRqst(newContactRequest);
        return newContactRequestRepository.save(newContactRequest);
    }

    //approves the rqst with the rqst id
    public NewContactRequest approveRqst(Long rqstId){
        NewContactRequest rqst = newContactRequestRepository.findById(rqstId).get();
        rqst.setCanAddNewContactRequester(true);
        rqst.setRequesterApprovalTimeStamp(LocalDate.now());
        return newContactRequestRepository.save(rqst);
    }

    public List<NewContactRequest> read(Long id){
        ChildUser childUser = childUserRepository.findById(id).get();
        if (childUser!=null) {
            return childUser.getRequests();
        }
        return new ArrayList<>();
    }
}

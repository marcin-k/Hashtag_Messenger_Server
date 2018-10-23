package com.marcin.hashtagmessenger.Approval;

import com.marcin.hashtagmessenger.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/rqst", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
public class NewContactRequestWebservice {

    @Autowired
    NewContactRequestService newContactRequestService;

    //get all requests for a child with child ID childId
    //http://localhost:8080/api/rqst/read?childId=3
    @GetMapping(path = "/read")
    public @ResponseBody
    List<NewContactRequest> read(@RequestParam String childId){
        Long id = Long.parseLong(childId);
        return newContactRequestService.read(id);
    }

    //creates a new request
    //http://localhost:8080/api/rqst/new
    @PostMapping(path = "/new")
    public @ResponseBody
    NewContactRequest newChildUser(@RequestBody NewContactRequest newContactRequest){
        return newContactRequestService.createRqst(newContactRequest);
    }

    //approves the request with the request id
    //http://localhost:8080/api/rqst/approve?rqstId=5
    @GetMapping(path="/approve")
    public @ResponseBody NewContactRequest approve(@RequestParam String rqstId){
        Long id = Long.parseLong(rqstId);
        return newContactRequestService.approveRqst(id);
    }


}

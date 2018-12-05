package com.marcin.hashtagmessenger.message;
/************************************************************
 * Class used to expose webservices.
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.List;


@Controller
@RequestMapping(path = "/api/msg", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
public class MessageWebservice {
    @Autowired
    MessageService messageService;

    //to send a new message (add to a database of messages)
    //http://localhost:8080/api/msg/new
    @PostMapping(path = "/new")
    public @ResponseBody
    Message newMsg(@RequestBody Message msg){
        return messageService.createMessage(msg);
    }

    //to get all messages for a user with a given ID
    //http://localhost:8080/api/msg/read?userId=2 where 2 is the id
    @GetMapping(path = "/read")
    public @ResponseBody List<Message> read(@RequestParam String userId){
        Long id = Long.parseLong(userId);
        return messageService.read(id);
    }

    //delete msg (GET)
    //http://localhost:8080/api/msg/delete?msgid=23
    @GetMapping(path = "/delete")
    public @ResponseBody String delete(@RequestParam String msgid){
        Long id = Long.parseLong(msgid);
        return messageService.delete(id);
    }

    //approve msg with id (GET)
    //http://localhost:8080/api/msg/approve?msgid=23
    @GetMapping(path = "/approve")
    public @ResponseBody String approve(@RequestParam String msgid){
        Long id = Long.parseLong(msgid);
        return messageService.approve(id);
    }

    //to get all sos for a parent with a given id
    //http://localhost:8080/api/msg/getSOS?userId=1 where 1 is the id
    @GetMapping(path = "/getSOS")
    public @ResponseBody List<Message> getSOS(@RequestParam String userId){
        Long id = Long.parseLong(userId);
        return messageService.getSOS(id);
    }

    //gets my kids messages for approval (GET) (send parent's id)
    //http://localhost:8080/api/msg/kidsmsgs?userId=1
    @GetMapping(path = "/kidsmsgs")
    public @ResponseBody List<Message> kidsmsgs(@RequestParam String userId){
        Long id = Long.parseLong(userId);
        return messageService.kidsmsgs(id);
    }
}

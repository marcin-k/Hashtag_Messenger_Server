package com.marcin.hashtagmessenger.message;

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

    //to get all messages for a given ID
    //http://localhost:8080/api/msg/read?userId=2 where 2 is the id
    @GetMapping(path = "/read")
    public @ResponseBody List<Message> read(@RequestParam String userId){
        Long id = Long.parseLong(userId);
        return messageService.read(id);
    }

}

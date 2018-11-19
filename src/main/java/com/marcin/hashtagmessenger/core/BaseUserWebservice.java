package com.marcin.hashtagmessenger.core;

import com.marcin.hashtagmessenger.childUser.ChildUser;
import com.marcin.hashtagmessenger.message.Message;
import com.marcin.hashtagmessenger.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/contacts", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
public class BaseUserWebservice {
    @Autowired
    BaseUserService baseUserService;

    //adds contact with id contactId to user with id userId
    //http://localhost:8080/api/contacts/new?contactId=2&userId=1
    @GetMapping(path = "/new")
    public @ResponseBody
    BaseUser newMsg(@RequestParam String contactId, @RequestParam String userId){
        Long idContact = Long.parseLong(contactId);
        Long idUser = Long.parseLong(userId);
        //if its the same id
        if (idContact == idUser){
            System.out.println("same id for contact and user");
            return null;
        }
        return baseUserService.createContact(idContact, idUser);
    }

    //to get all contacts for user with given ID
    //http://localhost:8080/api/contacts/read?userId=1
    // where 1 is the id
    @GetMapping(path = "/read")
    public @ResponseBody
    List<BaseUser> read(@RequestParam String userId){
        Long id = Long.parseLong(userId);
        return baseUserService.read(id);
    }

    //sets password for a user
    //http://localhost:8080/api/contacts/pw?userId=2&pw=testpass
    @GetMapping(path = "/pw")
    public @ResponseBody
    String setPassword(@RequestParam String userId, @RequestParam String pw){
        Long id = Long.parseLong(userId);
        return baseUserService.setPassword(id, pw);
    }

    //checks the username and password, returns user id
    //http://localhost:8080/api/contacts/login?username=user1&pw=user1
    @GetMapping(path = "/login")
    public @ResponseBody
    Long login(@RequestParam String username, @RequestParam String pw){
        return baseUserService.login(username, pw);
    }
}

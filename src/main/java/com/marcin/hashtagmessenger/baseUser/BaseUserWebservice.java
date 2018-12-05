package com.marcin.hashtagmessenger.baseUser;
/************************************************************
 * Class used to expose webservices.
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/
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
    String newMsg(@RequestParam String contactId, @RequestParam String userId){
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

    //returns list of users matching the criteria
    //http://localhost:8080/api/contacts/search?username=user1
    @GetMapping(path = "/search")
    public @ResponseBody
    List<BaseUser> login(@RequestParam String username){
        return baseUserService.search(username);
    }

    //returns 7 if free 0 if not
    //http://localhost:8080/api/contacts/checkLogin?username=user1
    @GetMapping(path = "/checkLogin")
    public @ResponseBody
    int checkLogin(@RequestParam String username){
        return baseUserService.checkLogin(username);
    }

    //returns 5 if its a parent 0 if child
    //http://localhost:8080/api/contacts/parentOrChild?id=1
    @GetMapping(path = "/parentOrChild")
    public @ResponseBody
    int parentOrChild(@RequestParam String id){
        Long idL = Long.parseLong(id);
        return baseUserService.parentOrChild(idL);
    }

    //returns string containing users id and first names (for performance improvement
    // that is cached on the client, so doesn't have to look up every time)
    //http://localhost:8080/api/contacts/cache
    @GetMapping(path = "/cache")
    public @ResponseBody
    String cache(){
        return baseUserService.cache();
    }
}

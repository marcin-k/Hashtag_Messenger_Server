package com.marcin.hashtagmessenger.parentUser;

import com.marcin.hashtagmessenger.childUser.ChildUser;
import com.marcin.hashtagmessenger.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/parent", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
public class ParentUserWebservice {

    @Autowired
    ParentUserService parentUserService;

    //creates a new parent user
    //http://localhost:8080/api/parent/new
    @PostMapping(path = "/new")
    public @ResponseBody
    ParentUser newParentUser(@RequestBody ParentUser parentUser){
        return parentUserService.createParentUser(parentUser);
    }

    //to get all children of a parent with a given ID
    //http://localhost:8080/api/parent/read?parentId=1 where 1 is the id of parent user
    @GetMapping(path = "/read")
    public @ResponseBody
    List<ChildUser> read(@RequestParam String parentId){
        Long id = Long.parseLong(parentId);
        return parentUserService.read(id);
    }
}

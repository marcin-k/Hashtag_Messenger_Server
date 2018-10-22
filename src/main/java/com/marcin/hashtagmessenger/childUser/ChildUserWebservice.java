package com.marcin.hashtagmessenger.childUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/child", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
public class ChildUserWebservice {

    @Autowired
    ChildUserService childUserService;

    //creates a new child user
    //http://localhost:8080/api/child/new?userId=2
    @PostMapping(path = "/new")
    public @ResponseBody
    ChildUser newChildUser(@RequestBody ChildUser childUser, @RequestParam String parentId){
        Long id = Long.parseLong(parentId);
        return childUserService.createChildUser(childUser, id);
    }


}

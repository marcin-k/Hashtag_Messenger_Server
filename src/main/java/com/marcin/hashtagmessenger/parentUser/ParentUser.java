package com.marcin.hashtagmessenger.parentUser;

import com.marcin.hashtagmessenger.childUser.ChildUser;
import com.marcin.hashtagmessenger.core.BaseUser;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@DiscriminatorValue("parent")
public class ParentUser extends BaseUser {
//-----------------------Class Variables--------------------------------------------------------------------------------
    private String emailAddress;
//TODO: set up many to many relationships
    @ManyToMany
    private List<ChildUser> children;

    //adds a child for parent user
    public void addNewChild(ChildUser childUser) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(childUser);
    }

//-----------------------Constructor------------------------------------------------------------------------------------
    protected ParentUser(){
        super();
    }

    public ParentUser(String userName, String firstName, String lastName, String password, String emailAddress) {
        super(userName, firstName, lastName, password);
        this.emailAddress = emailAddress;
    }
}

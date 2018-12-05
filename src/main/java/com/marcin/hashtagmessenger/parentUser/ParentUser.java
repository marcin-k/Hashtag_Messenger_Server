package com.marcin.hashtagmessenger.parentUser;

import com.fasterxml.jackson.annotation.*;
import com.marcin.hashtagmessenger.childUser.ChildUser;
import com.marcin.hashtagmessenger.baseUser.BaseUser;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/************************************************************
 * Parent user model class used to define the template of a
 * parent user
 *
 * author: Marcin Krzeminski
 *         x17158851
 * **************************************************************/

@Data
@Entity
@DiscriminatorValue("parent")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "parId")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class ParentUser extends BaseUser {
//-----------------------Class Variables--------------------------------------------------------------------------------
    private int parId;
    private String emailAddress;

    @ManyToMany(fetch = FetchType.EAGER)
//    @JsonManagedReference
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

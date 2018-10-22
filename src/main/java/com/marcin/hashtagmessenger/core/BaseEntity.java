package com.marcin.hashtagmessenger.core;

//import lombok.Data;

import javax.persistence.*;

//@Data
@MappedSuperclass
public class BaseEntity {

//-----------------------Class Variables--------------------------------------------------------------------------------
    //all of the tables will include id therefore its defined in the base entity class
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    //version will control the version of data cashed on parentUser device
    @Version
    private Long version;

//-----------------------Constructor------------------------------------------------------------------------------------
    protected BaseEntity(){
        id = null;
    }
}

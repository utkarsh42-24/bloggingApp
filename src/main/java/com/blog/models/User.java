package com.blog.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @Column(nullable = false, unique = true)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long userID;

    @Column(nullable = false)
    String name;

}

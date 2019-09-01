package com.blog.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Blog {

    @Id
    @Column(unique = true, nullable = false)
    Long blogID;

    @Column(nullable = false, length = 50)
    String description;

}

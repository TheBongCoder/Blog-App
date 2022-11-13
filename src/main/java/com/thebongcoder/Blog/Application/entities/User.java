package com.thebongcoder.Blog.Application.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private String email;

    private String password;

    @Column(columnDefinition = "TEXT")
    private String about;
}

package com.noroff.mefit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "user_id")
    public int user_id;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "password")
    public String password;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    public String first_name;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "last_name")
    public String last_name;

    @Getter @Setter
    @Column(name = "isContributor")
    public Boolean isContributor;

    @Getter @Setter
    @Column(name = "isAdmin")
    public Boolean isAdmin;
}

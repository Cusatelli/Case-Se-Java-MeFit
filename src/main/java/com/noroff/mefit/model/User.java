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
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "id")
    public Long id;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "password")
    public String password;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    public String firstName;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "last_name")
    public String lastName;

    @Getter @Setter
    @Column(name = "contributor")
    public Boolean contributor;

    @Getter @Setter
    @Column(name = "admin")
    public Boolean admin;
}

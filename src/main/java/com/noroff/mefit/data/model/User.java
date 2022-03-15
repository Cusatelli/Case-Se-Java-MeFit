package com.noroff.mefit.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "user", schema = "public")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    @Size(min = 1, max = 100)
    public String password;

    @Column
    public String firstName;

    @Column
    public String lastName;

    @Column
    public Boolean contributor;

    @Column
    public Boolean admin;
}

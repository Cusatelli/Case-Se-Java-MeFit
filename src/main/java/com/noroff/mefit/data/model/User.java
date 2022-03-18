package com.noroff.mefit.data.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Table(name = "user", schema = "public")
public class User {
    @Id
    @Column
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;


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

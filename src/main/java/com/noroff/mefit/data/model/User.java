package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonIgnore
    @OneToOne(
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    private Profile profile;
}

package com.noroff.mefit.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "public")
public class User {
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    public Integer id;

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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "profile_user",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "profile_id") }
    )
    private Profile profile;
}

package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile", schema = "public")
public class Profile {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer weight;

    @Column
    private Integer height;

    @Column
    private String medicalConditions;

    @Column
    private String disabilities;

    @OneToOne(mappedBy = "profile")
    private Address address;

    @ManyToMany(mappedBy = "profiles")
    private List<Goal> goals;

    @ManyToMany(mappedBy = "profiles")
    private List<Program> programs;

    @ManyToMany(mappedBy = "profiles")
    private List<Set> sets;

    @JsonIgnore
    @OneToOne(mappedBy = "profile")
    private User user;

    @ManyToMany(mappedBy = "profiles")
    private List<Workout> workouts;
}

package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @OneToMany(mappedBy = "profile")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Address> addresses;

    @ManyToMany(mappedBy = "profiles")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Goal> goals;

    @ManyToMany(mappedBy = "profiles")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Program> programs;

    @ManyToMany(mappedBy = "profiles")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Set> sets;

    @OneToOne(mappedBy = "profile")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private User user;

    @ManyToMany(mappedBy = "profiles")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Workout> workouts;
}

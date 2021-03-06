package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "set", schema = "public")
public class Set {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    public Integer exerciseRepetition;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinTable(name = "set_exercise",
            joinColumns = { @JoinColumn(name = "set_id") },
            inverseJoinColumns = { @JoinColumn(name = "exercise_id") }
    )
    private Exercise exercise;

    @JsonIgnore
    @ManyToMany(mappedBy = "sets")
    private List<Workout> workouts;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "profile_set",
            joinColumns = { @JoinColumn(name = "profile_id") },
            inverseJoinColumns = { @JoinColumn(name = "set_id") }
    )
    private List<Profile> profiles = new ArrayList<>();
}

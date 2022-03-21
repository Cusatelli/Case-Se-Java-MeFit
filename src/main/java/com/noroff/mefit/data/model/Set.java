package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @OneToOne
    @JoinTable(name = "set_exercise",
            joinColumns = { @JoinColumn(name = "set_id") },
            inverseJoinColumns = { @JoinColumn(name = "exercise_id") }
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Exercise exercise;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "set"
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Workout> workouts;

    @JsonIgnore
    @ManyToMany(
            cascade = CascadeType.ALL,
            mappedBy = "sets"
    )
    private List<Profile> profiles = new ArrayList<>();
}

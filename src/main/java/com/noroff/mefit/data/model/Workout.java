package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workout", schema = "public")
public class Workout {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String type;

    @NotNull
    @Column(nullable = false)
    private Boolean complete;

    @ManyToOne
    @JoinTable(name = "workout_set",
            joinColumns = { @JoinColumn(name = "workout_id") },
            inverseJoinColumns = { @JoinColumn(name = "set_id") }
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set set;

    @JsonIgnore
    @OneToMany(mappedBy = "workout")
    private List<Goal> goals;

    @JsonIgnore
    @ManyToMany(
            cascade = CascadeType.ALL,
            mappedBy = "workouts"
    )
    private List<Profile> profiles = new ArrayList<>();
}

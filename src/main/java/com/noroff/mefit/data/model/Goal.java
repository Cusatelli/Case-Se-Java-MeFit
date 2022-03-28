package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "goal", schema = "public")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Timestamp endDate;

    @NotNull
    @Column(nullable = false)
    private Boolean achieved;

    @OneToOne(mappedBy = "goal")
    private Program program;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "goal_workout",
            joinColumns = { @JoinColumn(name = "goal_id") },
            inverseJoinColumns = { @JoinColumn(name = "workout_id") }
    )
    private List<Workout> workouts;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "profile_goal",
            joinColumns = { @JoinColumn(name = "profile_id") },
            inverseJoinColumns = { @JoinColumn(name = "goal_id") }
    )
    private List<Profile> profiles = new ArrayList<>();
}

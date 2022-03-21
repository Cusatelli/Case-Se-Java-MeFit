package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Program program;

    @ManyToOne
    @JoinTable(name = "goal_workout",
            joinColumns = { @JoinColumn(name = "goal_id") },
            inverseJoinColumns = { @JoinColumn(name = "workout_id") }
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Workout workout;

    @JsonIgnore
    @ManyToMany(
            cascade = CascadeType.ALL,
            mappedBy = "goals"
    )
    private List<Profile> profiles = new ArrayList<>();
}

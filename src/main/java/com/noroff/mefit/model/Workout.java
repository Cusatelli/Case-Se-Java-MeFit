package com.noroff.mefit.model;

import com.noroff.mefit.model.type.WorkoutType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workout", schema = "public")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Getter @Setter
    @Column(name = "type", nullable = false)
    private WorkoutType type;

    @Getter @Setter
    @Column(name = "complete", nullable = false)
    private Boolean complete;

    // TODO: Add Set Model Relationship.
}

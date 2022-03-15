package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "program", schema = "public")
public class ProgramWorkout {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workout_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Workout workout;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "program_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Program program;
}

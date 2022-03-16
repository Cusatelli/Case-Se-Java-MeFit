package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "goal_workout", schema = "public")
public class GoalWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "end_date")
    private Timestamp endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workout_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Workout workout;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "goal_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Goal goal;
}
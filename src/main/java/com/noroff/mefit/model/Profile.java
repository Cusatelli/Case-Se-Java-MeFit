package com.noroff.mefit.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile", schema = "public")
@Getter
@Setter
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 1, max = 10)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private User userId;

//    @Column(name = "goal_id")
//    private Integer goalId;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id")
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    private Address addressId;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "program_id")
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    private Program programId;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "workout_id")
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    private Workout workoutId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "set_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set setId;

    @Size(min = 1, max = 5)
    @Column(name = "weight")
    private Integer weight;

    @Size(min = 1, max = 3)
    @Column(name = "height")
    private Integer height;

    @Column(name = "medicalConditions")
    private String medicalConditions;

    @Getter @Setter
    @Column(name = "disabilities")
    private String disabilities;
}

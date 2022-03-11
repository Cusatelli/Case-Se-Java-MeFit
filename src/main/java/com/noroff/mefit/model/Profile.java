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
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private Integer id;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private User userId;

//    @Getter @Setter
//    @Size(min = 1, max = 50)
//    @Column(name = "goal_id")
//    private Integer goalId;

//    @Getter @Setter
//    @Size(min = 1, max = 50)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id")
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    private Address addressId;

//    @Getter @Setter
//    @Size(min = 1, max = 50)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "program_id")
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    private Program programId;

//    @Getter @Setter
//    @Size(min = 1, max = 50)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "workout_id")
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    private Workout workoutId;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "set_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set setId;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "weight")
    private Integer weight;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "height")
    private Integer height;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "medicalConditions")
    private String medicalConditions;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "disabilities")
    private String disabilities;
}

package com.noroff.mefit.data.model;

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
@Getter @Setter
@Table(name = "profile", schema = "public")
public class Profile {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private User user;

    @Column(name = "goal_id")
    private Long goal;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "program_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Program program;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workout_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Workout workout;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set set;

    @Column
    @Size(min = 1, max = 5)
    private Integer weight;

    @Column
    @Size(min = 1, max = 3)
    private Integer height;

    @Column
    private String medicalConditions;

    @Column
    private String disabilities;
}

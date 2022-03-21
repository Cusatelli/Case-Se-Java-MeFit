package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile", schema = "public")
public class Profile {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "profile_user",
            joinColumns = { @JoinColumn(name = "profile_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private User user;

    @ManyToMany
    @JoinTable(name = "profile_goal",
            joinColumns = { @JoinColumn(name = "profile_id") },
            inverseJoinColumns = { @JoinColumn(name = "goal_id") }
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Goal> goals;

    @OneToOne
    @JoinTable(name = "profile_address",
            joinColumns = { @JoinColumn(name = "profile_id") },
            inverseJoinColumns = { @JoinColumn(name = "address_id") }
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Address address;

    @ManyToMany
    @JoinTable(name = "profile_program",
            joinColumns = {@JoinColumn(name = "profile_id")},
            inverseJoinColumns = {@JoinColumn(name = "program_id")}
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Program> programs;

    @ManyToMany
    @JoinTable(name = "profile_workout",
            joinColumns = { @JoinColumn(name = "profile_id") },
            inverseJoinColumns = { @JoinColumn(name = "workout_id") }
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Workout> workouts;

    @ManyToMany
    @JoinTable(name = "profile_set",
            joinColumns = { @JoinColumn(name = "profile_id") },
            inverseJoinColumns = { @JoinColumn(name = "set_id") }
    )
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Set> sets;
}

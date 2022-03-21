package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "program", schema = "public")
public class Program {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(nullable = false)
    private String name;

    @Column
    @Size(max = 100)
    private String category;

    @JsonIgnore
    @ManyToMany(mappedBy = "programs")
    private List<Profile> profiles = new ArrayList<>();

    @JsonIgnore
    @OneToOne
    @JoinTable(name = "program_goal",
            joinColumns = { @JoinColumn(name = "program_id") },
            inverseJoinColumns = { @JoinColumn(name = "goal_id") }
    )
    private Goal goal;
}

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
@Getter @Setter
@Table(name = "set", schema = "public")
public class Set {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    public Integer exerciseRepetition;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exercise_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Exercise exercise;
}

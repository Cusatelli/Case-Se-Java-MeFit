package com.noroff.mefit.data.model;

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
@Table(name = "exercise", schema = "public")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String name;

    @Column
    @Size(min = 1, max = 100)
    public String description;

    @Column
    public String targetMuscleGroup;

    @Column
    public String image;

    // Set name to "vid_link" in database.
    @Column(name = "vid_link")
    public String videoLink;
}

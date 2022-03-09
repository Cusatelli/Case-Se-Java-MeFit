package com.noroff.mefit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exercise", schema = "public")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    public Long id;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "name")
    public String name;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "description")
    public String description;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "target_muscle_group")
    public String target_muscle_group;

    @Getter @Setter
    @Column(name = "image")
    public String image;

    @Getter @Setter
    @Column(name = "vid_link")
    public String vid_link;
}

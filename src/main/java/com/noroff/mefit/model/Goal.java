package com.noroff.mefit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goal", schema = "public")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private int id;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "end_date")
    private LocalDate endDate;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "achieved")
    private String achieved;
}

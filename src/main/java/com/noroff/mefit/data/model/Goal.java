package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "goal", schema = "public")
public class Goal {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Getter @Setter
    private Timestamp endDate;

    @NotNull
    @Getter @Setter
    @Column(nullable = false)
    private Boolean achieved;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "program_id")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Program program;
}

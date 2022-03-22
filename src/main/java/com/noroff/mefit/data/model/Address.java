package com.noroff.mefit.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address", schema = "public")
public class Address {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "address_line_3")
    private String addressLine3;

    // Longest PostalCode is 10.
    // Recommended field size 12 char to accommodate future changes.
    @Size(max = 12)
    @Column(nullable = false)
    private String postalCode;

    // Longest City name is 58.
    // Wales: "Llanfair¬pwllgwyngyll¬gogery¬chwyrn¬drobwll¬llan¬tysilio¬gogo¬goch"
    @Size(max = 70)
    @Column(nullable = false)
    private String city;

    // Longest Country name is 56.
    // "The United Kingdom of Great Britain and Northern Ireland "
    @Size(max = 70)
    @Column(nullable = false)
    private String country;

    @JsonIgnore
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    @JoinTable(name = "profile_address",
            joinColumns = { @JoinColumn(name = "profile_id") },
            inverseJoinColumns = { @JoinColumn(name = "address_id") }
    )
    private Profile profile;
}

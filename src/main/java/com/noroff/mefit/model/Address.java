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
@Table(name = "address", schema = "public")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private Integer id;

    @Getter @Setter
    @Column(name = "address_line_1")
    private String addressLine1;

    @Getter @Setter
    @Column(name = "address_line_2")
    private String addressLine2;

    @Getter @Setter
    @Column(name = "address_line_3")
    private String addressLine3;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "postal_code")
    private String postalCode;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "city")
    private String city;

    @Getter @Setter
    @Size(min = 1, max = 50)
    @Column(name = "country")
    private String country;
}

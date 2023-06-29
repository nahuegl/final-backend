package com.dh.SessionBookingSystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Getter @Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private Integer number;
    private String town;
    private String state;

    public Address() {
    }

    public Address(String street, Integer number, String town, String state) {

        this.street = street;
        this.number = number;
        this.town = town;
        this.state = state;
    }
    
}

package com.dh.SessionBookingSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dentists")
@Getter @Setter
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String license;

    @JsonIgnore
    @OneToMany(mappedBy = "dentist", fetch = FetchType.EAGER)
    private Set<Appointment> appointmentSet = new HashSet<>();

    public Dentist() {
    }

    public Dentist(String name, String lastName, String license) {
        this.name = name;
        this.lastName = lastName;
        this.license = license;
    }

    public Dentist(Long id, String name, String lastName, String license) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.license = license;
    }

}

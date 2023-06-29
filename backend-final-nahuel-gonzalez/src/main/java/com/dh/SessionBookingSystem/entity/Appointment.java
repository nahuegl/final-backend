package com.dh.SessionBookingSystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "appointments")
@Getter @Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id")
    private Dentist dentist;
    private LocalDate appointmentDate;

    public Appointment() {
    }

    public Appointment(Long id, Patient patient, Dentist dentist, LocalDate appointmentDate) {
        this.id = id;
        this.patient = patient;
        this.dentist = dentist;
        this.appointmentDate = appointmentDate;
    }

    public Appointment(Patient patient, Dentist dentist, LocalDate appointmentDate) {
        this.patient = patient;
        this.dentist = dentist;
        this.appointmentDate = appointmentDate;
    }

}

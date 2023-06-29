package com.dh.SessionBookingSystem.repository;

import com.dh.SessionBookingSystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByNameAndLastName(String name, String lastname);
}

package com.dh.SessionBookingSystem.repository;

import com.dh.SessionBookingSystem.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {

    Optional<Dentist> findByNameAndLastName(String name, String lastName);

}

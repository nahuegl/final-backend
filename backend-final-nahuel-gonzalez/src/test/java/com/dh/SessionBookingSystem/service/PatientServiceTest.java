package com.dh.SessionBookingSystem.service;
import com.dh.SessionBookingSystem.entity.Address;
import com.dh.SessionBookingSystem.entity.Patient;
import com.dh.SessionBookingSystem.exception.BadRequestException;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PatientServiceTest {
    @Autowired
    private PatientService patientService;
    @Test
    @Order(1)
    public void savePatientTest() {
        Patient patientToSave = new Patient("Nahuel", "Gonzalez", 34378395, new Address("Luis Lhoner", 730, "Haedo", "Buenos Aires"),LocalDate.of(2023, 6, 29), "glnahuel17@gmail.com");
        Patient patientSaved = patientService.save(patientToSave);

        assertEquals(1L, patientSaved.getId());
    }

    @Test
    @Order(2)
    public void findByIdTest() throws ResourceNotFoundException {
        Long searchedId = 1L;
        Optional<Patient> patientSearched = patientService.findById(searchedId);

        assertNotNull(patientSearched.get());
    }

    @Test
    @Order(3)
    public void findAllTest() throws ResourceNotFoundException {
        List<Patient> patients = patientService.findAll();
        Integer expectedQuantity = 1;

        assertEquals(expectedQuantity, patients.size());
    }

    @Test
    @Order(4)
    public void updateTest() throws BadRequestException, ResourceNotFoundException {
        Patient patientToUpdate = new Patient(1L, "Jose", "Fernandez", 94222312, new Address("Calle Falsa", 123, "Springfield", "Texas"), LocalDate.of(1990, 10, 5), "jfernandez@gmail.com");
        patientService.save(patientToUpdate);
        patientService.update(patientToUpdate);
        Optional<Patient> updatedPatient = patientService.findById(patientToUpdate.getId());

        assertEquals("Jose", updatedPatient.get().getName());
    }

    @Test
    @Order(5)
    public void deleteByIdTest() throws ResourceNotFoundException {
        Long idToDelete = 1L;
        patientService.deleteById(idToDelete);

        Optional<Patient> deletedPatient = patientService.findById(idToDelete);

        assertFalse(deletedPatient.isPresent());
    }
}
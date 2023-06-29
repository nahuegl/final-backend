package com.dh.SessionBookingSystem.service;

import com.dh.SessionBookingSystem.entity.Dentist;
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
public class DentistServiceTest {

    @Autowired
    private DentistService dentistService;

    @Test
    @Order(1)
    public void saveDentistTest () throws BadRequestException {
        Dentist dentistToSave = new Dentist("Juan", "Garcia", "56789");
        Dentist dentistSaved = dentistService.save(dentistToSave);

        assertEquals(1L, dentistSaved.getId());
    }

    @Test
    @Order(2)
    public void findByIdTest() throws ResourceNotFoundException {
        Long searchedId = 1L;
        Optional<Dentist> dentistSearched = dentistService.findById(searchedId);

        assertNotNull(dentistSearched.get());
    }

    @Test
    @Order(3)
    public void findAllTests () throws ResourceNotFoundException {
        List<Dentist> dentists = dentistService.findAll();
        Integer expectedQuantity = 1;

        assertEquals(expectedQuantity, dentists.size());
    }

    @Test
    @Order(4)
    public void updateTest() throws BadRequestException, ResourceNotFoundException {
        Dentist dentistToUpdate = new Dentist("Carlos", "Perez", "7722");
        dentistService.save(dentistToUpdate);
        dentistService.update(dentistToUpdate);
        Optional<Dentist> updatedDentist = dentistService.findById(dentistToUpdate.getId());

        assertEquals("Carlos", updatedDentist.get().getName());
    }

    @Test
    @Order(5)
    public void deleteByIdTest() throws ResourceNotFoundException {
        Long idToDelete = 1L;
        dentistService.deleteById(idToDelete);

        Optional<Dentist> deletedDentist = dentistService.findById(idToDelete);

        assertFalse(deletedDentist.isPresent());
    }
}
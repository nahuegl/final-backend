package com.dh.SessionBookingSystem.service;

import com.dh.SessionBookingSystem.dto.AppointmentDTO;
import com.dh.SessionBookingSystem.entity.Address;
import com.dh.SessionBookingSystem.entity.Dentist;
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
public class AppointmentServiceTest {

    private final PatientService patientService;
    private final DentistService dentistService;
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentServiceTest(PatientService patientService, DentistService dentistService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.dentistService = dentistService;
        this.appointmentService = appointmentService;
    }

    @Test
    @Order(1)
    public void saveAppointment() throws ResourceNotFoundException, BadRequestException {
        Patient patientToSave = patientService.save(new Patient("Nahuel", "Gonzalez", 34378395, new Address("Luis Lhoner", 730, "Haedo", "Buenos Aires"), LocalDate.of(2023, 06, 29), "glnahuel17@gmail.com"));
        Dentist dentistToSave = dentistService.save(new Dentist("Juan", "Garcia", "56789"));

        AppointmentDTO appointmentToSave = new AppointmentDTO();
        appointmentToSave.setPatientId(patientToSave.getId());
        appointmentToSave.setDentistId(dentistToSave.getId());
        appointmentToSave.setAppointmentDate(LocalDate.of(2023, 06, 29));
        AppointmentDTO appointmentDTOSaved = appointmentService.save(appointmentToSave);

        assertEquals(1L, appointmentDTOSaved.getId());
    }

    @Test
    @Order(2)
    public void findByIdTest() throws ResourceNotFoundException {
        Long searchedId = 1L;
        Optional<AppointmentDTO> appointmentSearched = appointmentService.findById(searchedId);

        assertNotNull(appointmentSearched.get());
    }

    @Test
    @Order(3)
    public void findAllTests() throws ResourceNotFoundException {
        List<AppointmentDTO> appointments = appointmentService.findAll();

        assertTrue(appointments.isEmpty());
    }

    @Test
    @Order(4)
    public void updateTest() throws BadRequestException, ResourceNotFoundException {
        Patient patientToUpdate = patientService.save(new Patient("Jose", "Fernandez", 94222312, new Address("Calle Falsa", 123, "Springfield", "Texas"), LocalDate.of(1990, 10, 5), "jfernandez@gmail.com"));
        Dentist dentistToUpdate = dentistService.save(new Dentist("Carlos", "Perez", "7722"));
        LocalDate newDate = LocalDate.of(2000, 2, 1);

        AppointmentDTO appointmentToUpdate = new AppointmentDTO();
        appointmentToUpdate.setId(1L);
        appointmentToUpdate.setPatientId(patientToUpdate.getId());
        appointmentToUpdate.setDentistId(dentistToUpdate.getId());
        appointmentToUpdate.setAppointmentDate(newDate);
        appointmentService.save(appointmentToUpdate);
        appointmentService.update(appointmentToUpdate);
        Optional<AppointmentDTO> appointmentUpdated = appointmentService.findById(appointmentToUpdate.getId());

        assertEquals(newDate, appointmentUpdated.get().getAppointmentDate());
    }

    @Test
    @Order(5)
    public void deleteByIdTest() throws ResourceNotFoundException {
        Long idToDelete = 1L;
        appointmentService.deleteById(idToDelete);


        ResourceNotFoundException thrown = assertThrows(
                ResourceNotFoundException.class,
                () -> appointmentService.findById(idToDelete).isPresent()
        );

        assertTrue(thrown.getMessage().contains("Don't find any appointment with id: " + idToDelete + ". Try again."));
    }
}
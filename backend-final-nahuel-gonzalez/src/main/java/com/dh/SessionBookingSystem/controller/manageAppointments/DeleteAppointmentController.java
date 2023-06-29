package com.dh.SessionBookingSystem.controller.manageAppointments;

import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.service.AppointmentService;
import com.dh.SessionBookingSystem.service.DentistService;
import com.dh.SessionBookingSystem.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/deleteAppointment")

public class DeleteAppointmentController {

    private AppointmentService appointmentService;
    private DentistService dentistService;
    private PatientService patientService;

    private final Logger LOGGER = Logger.getLogger(DeleteAppointmentController.class);

    @Autowired
    public DeleteAppointmentController(AppointmentService appointmentService, DentistService dentistService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    @DeleteMapping("/{id}")
    public void DeleteAppointmentById(@PathVariable Long id) throws ResourceNotFoundException {

        LOGGER.info("Request send: you are trying to delete an Appointment with id: " + id + ".");
        if (appointmentService.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Can't delete. Patient or dentist does not exist in the database.");
        }
        appointmentService.deleteById(id);
        LOGGER.info("Delete appointment with id: "+ id + ".");

    }
}

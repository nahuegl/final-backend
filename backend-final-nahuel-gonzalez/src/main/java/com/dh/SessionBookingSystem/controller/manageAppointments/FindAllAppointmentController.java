package com.dh.SessionBookingSystem.controller.manageAppointments;

import com.dh.SessionBookingSystem.dto.AppointmentDTO;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.service.AppointmentService;
import com.dh.SessionBookingSystem.service.DentistService;
import com.dh.SessionBookingSystem.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/findAllAppointment")

public class FindAllAppointmentController {

    private AppointmentService appointmentService;
    private DentistService dentistService;
    private PatientService patientService;

    private final Logger LOGGER = Logger.getLogger(FindAllAppointmentController.class);

    @Autowired
    public FindAllAppointmentController(AppointmentService appointmentService, DentistService dentistService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> findAll() throws ResourceNotFoundException {
        LOGGER.info("Request send: you are trying to list all appointments.");
        return ResponseEntity.ok(appointmentService.findAll());
    }

}

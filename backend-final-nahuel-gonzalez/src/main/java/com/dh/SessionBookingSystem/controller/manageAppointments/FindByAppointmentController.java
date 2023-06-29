package com.dh.SessionBookingSystem.controller.manageAppointments;

import com.dh.SessionBookingSystem.dto.AppointmentDTO;
import com.dh.SessionBookingSystem.exception.BadRequestException;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.service.AppointmentService;
import com.dh.SessionBookingSystem.service.DentistService;
import com.dh.SessionBookingSystem.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/findAppointment")

public class FindByAppointmentController {

    private AppointmentService appointmentService;
    private DentistService dentistService;
    private PatientService patientService;

    private final Logger LOGGER = Logger.getLogger(FindByAppointmentController.class);

    @Autowired
    public FindByAppointmentController(AppointmentService appointmentService, DentistService dentistService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    @GetMapping("/byId")
    public ResponseEntity<Optional<AppointmentDTO>> findByAppointmentById(@RequestParam Long id) throws ResourceNotFoundException {

        LOGGER.info("Request send: you are trying to find an Appointment.");
        Optional<AppointmentDTO> searchAppointmentDTO = appointmentService.findById(id);
        if (searchAppointmentDTO.isEmpty()) {
            throw new ResourceNotFoundException("Don't find any appointment with id: " + id + ". Try again.");
        }
        return ResponseEntity.status(200).body(searchAppointmentDTO);
    }

}

package com.dh.SessionBookingSystem.controller.manageAppointments;

import com.dh.SessionBookingSystem.dto.AppointmentDTO;
import com.dh.SessionBookingSystem.exception.BadRequestException;
import com.dh.SessionBookingSystem.exception.ResourceNotFoundException;
import com.dh.SessionBookingSystem.service.AppointmentService;
import com.dh.SessionBookingSystem.service.DentistService;
import com.dh.SessionBookingSystem.service.PatientService;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/updateAppointment")

public class UpdateAppointmentController {

    private AppointmentService appointmentService;
    private DentistService dentistService;
    private PatientService patientService;

    private final Logger LOGGER = Logger.getLogger(UpdateAppointmentController.class);

    @PutMapping
    public void update(@RequestBody AppointmentDTO appointmentDTO) throws ResourceNotFoundException, BadRequestException {

        LOGGER.info("Request send: you are trying to delete an Appointment with id: " + appointmentDTO.getId() + ".");
        ResponseEntity<AppointmentDTO> response;

        if (appointmentService.findById(appointmentDTO.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Don't find any appointment with id: " + appointmentDTO.getId() + ". Try again.");
        }
        else if (patientService.findById(appointmentDTO.getPatientId()).isEmpty() || dentistService.findById(appointmentDTO.getDentistId()).isEmpty()) {
           throw new BadRequestException("Can't update. Patient or dentist does not exist in the database.");
        }
        LOGGER.info("Update appointment with id: " + appointmentDTO.getId());
        appointmentService.update(appointmentDTO);

    }

}

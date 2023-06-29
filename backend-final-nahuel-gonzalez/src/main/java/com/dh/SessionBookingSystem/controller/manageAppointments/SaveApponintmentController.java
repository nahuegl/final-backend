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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saveAppointment")

public class SaveApponintmentController {

    private AppointmentService appointmentService;
    private DentistService dentistService;
    private PatientService patientService;

    private final Logger LOGGER = Logger.getLogger(SaveApponintmentController.class);

    @Autowired
    public SaveApponintmentController(AppointmentService appointmentService, DentistService dentistService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.dentistService = dentistService;
        this.patientService = patientService;
    }

    @PostMapping
    public void save(@RequestBody AppointmentDTO appointmentDTO) throws BadRequestException, ResourceNotFoundException {

        LOGGER.info("Request send: you are trying to save an Appointment with date: " + appointmentDTO.getAppointmentDate() + ".");
        ResponseEntity<AppointmentDTO> response;
        if (patientService.findById(appointmentDTO.getPatientId()).isEmpty() ||
            dentistService.findById(appointmentDTO.getDentistId()).isEmpty()) {
            throw new BadRequestException("Can't save. Patient or dentist does not exist in the database.");
        }
        appointmentService.save(appointmentDTO);
        LOGGER.info("Add an Appointment with date: " + appointmentDTO.getAppointmentDate() + ".");

    }

}

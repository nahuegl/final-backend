package com.dh.SessionBookingSystem.exception;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    private static final Logger LOGGER = Logger.getLogger(GlobalException.class);
    @ExceptionHandler({ResourceNotFoundException.class})

    public ResponseEntity<String> processResourceNotFoundException(ResourceNotFoundException rnfe){
        LOGGER.error("Error, the system detected a problem, the following message was logged: "+
                rnfe.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processBadRequestException(BadRequestException bre){
        LOGGER.error("Error, the system detected a problem, the following message was logged: "+
                bre.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bre.getMessage());
    }

}

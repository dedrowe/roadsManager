package com.streetsmanager.exception_handling;

import com.streetsmanager.exception_handling.exceptions.RoadBaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.postgresql.util.PSQLException;

import java.util.Objects;

@ControllerAdvice
public class RoadsExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionInformation> exception(PSQLException ex) {
        ExceptionInformation exceptionInformation = new ExceptionInformation();
        exceptionInformation.setInfo(Objects.requireNonNull(ex.getServerErrorMessage()).getMessage());

        return new ResponseEntity<>(exceptionInformation, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionInformation> exception(RoadBaseException ex) {
        ExceptionInformation exceptionInformation = new ExceptionInformation();
        exceptionInformation.setInfo(ex.getMessage());

        return new ResponseEntity<>(exceptionInformation, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionInformation> exception(HttpMessageNotReadableException ex) {
        ExceptionInformation exceptionInformation = new ExceptionInformation();
        exceptionInformation.setInfo(ex.getMessage());

        return new ResponseEntity<>(exceptionInformation, HttpStatus.BAD_REQUEST);
    }

}

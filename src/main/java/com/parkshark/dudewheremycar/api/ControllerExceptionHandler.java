package com.parkshark.dudewheremycar.api;

import com.parkshark.dudewheremycar.domain.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(InvalidParkingLotInformationException.class)
    protected void authorizationNotGranted(InvalidParkingLotInformationException ex,
                                           HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidAddressInformationException.class)
    protected void authorizationNotGranted(InvalidAddressInformationException ex,
                                           HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidCityInformationException.class)
    protected void authorizationNotGranted(InvalidCityInformationException ex,
                                           HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidContactPersonInformationException.class)
    protected void authorizationNotGranted(InvalidContactPersonInformationException ex,
                                           HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidEmailAddressInformationException.class)
    protected void authorizationNotGranted(InvalidEmailAddressInformationException ex,
                                           HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidDivisionInformationException.class)
    protected void authorizationNotGranted(InvalidDivisionInformationException ex, HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidDirectorInformationException.class)
    protected void authorizationNotGranted(InvalidDirectorInformationException ex, HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    private void badRequest(Exception ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
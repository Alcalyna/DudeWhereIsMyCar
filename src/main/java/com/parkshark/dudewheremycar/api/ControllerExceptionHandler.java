package com.parkshark.dudewheremycar.api;

import com.parkshark.dudewheremycar.domain.exceptions.InvalidDivisionIdException;
import com.parkshark.dudewheremycar.domain.exceptions.ParkingSpotAllocationException;
import com.parkshark.dudewheremycar.domain.exceptions.information.*;
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
    protected void invalidParkingLotInformationException(InvalidParkingLotInformationException ex,
                                           HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidAddressInformationException.class)
    protected void invalidAddressInformationException(InvalidAddressInformationException ex,
                                           HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidCityInformationException.class)
    protected void invalidCityInformationException(InvalidCityInformationException ex,
                                           HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidContactPersonInformationException.class)
    protected void invalidContactPersonInformationException(InvalidContactPersonInformationException ex,
                                           HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidEmailAddressInformationException.class)
    protected void invalidEmailAddressInformationException(InvalidEmailAddressInformationException ex,
                                           HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidDivisionInformationException.class)
    protected void invalidDivisionInformationException(InvalidDivisionInformationException ex, HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidDirectorInformationException.class)
    protected void invalidDirectorInformationException(InvalidDirectorInformationException ex, HttpServletResponse response) throws IOException {
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidMemberInformationException.class)
    protected void invalidMemberInformationException(InvalidMemberInformationException ex,
                                         HttpServletResponse response) throws IOException{
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidParkingSpotAllocationInformationException.class)
    protected void invalidParkingSpotAllocationInformationException(InvalidParkingSpotAllocationInformationException ex,
                                                     HttpServletResponse response) throws IOException{
        badRequest(ex, response);
    }

    @ExceptionHandler(ParkingSpotAllocationException.class)
    protected void parkingSpotAllocationException(ParkingSpotAllocationException ex,
                                                  HttpServletResponse response) throws IOException{
        badRequest(ex, response);
    }

    @ExceptionHandler(InvalidDivisionIdException.class)
    protected void invalidMemberInformationException(InvalidDivisionIdException ex,
                                                     HttpServletResponse response) throws IOException{
        badRequest(ex, response);
    }

    private void badRequest(Exception ex, HttpServletResponse response) throws IOException {
        logger.error(ex.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
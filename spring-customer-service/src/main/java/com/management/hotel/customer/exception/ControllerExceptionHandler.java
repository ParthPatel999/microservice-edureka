package com.management.hotel.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = NoSuchCustomerExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleException(NoSuchCustomerExistsException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),"No Customer found!!");
    }

    @ExceptionHandler(value = CustomerAlreadyPresentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody ErrorResponse handleExceptionForCustomerPresent(CustomerAlreadyPresentException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(),"Customer Already Present!!");
    }
}

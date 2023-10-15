package com.management.hotel.customer.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class NoSuchCustomerExistsException extends RuntimeException {
    private String message;
}

package com.management.hotel.customer.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CustomerAlreadyPresentException extends RuntimeException {
    private String message;
}

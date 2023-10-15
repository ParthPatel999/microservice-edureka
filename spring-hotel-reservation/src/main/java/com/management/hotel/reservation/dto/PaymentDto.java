package com.management.hotel.reservation.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDto {

    private Long id;

    private Long customerId;

    private BigDecimal amount;

    private String method;

    private boolean status;

}

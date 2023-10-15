package com.management.hotel.reservation.client;

import com.management.hotel.reservation.dto.HotelManagementDto;
import com.management.hotel.reservation.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "payment-service", url = "http://localhost:8093", path = "/api/v1/payments")
public interface PaymentClient {

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable("id") Long id);

    @PostMapping
    public PaymentDto makePayment(@RequestBody PaymentDto paymentDto);
}

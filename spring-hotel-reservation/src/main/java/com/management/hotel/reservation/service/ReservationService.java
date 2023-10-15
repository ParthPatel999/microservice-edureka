package com.management.hotel.reservation.service;

import com.management.hotel.reservation.client.ManagementClient;
import com.management.hotel.reservation.client.PaymentClient;
import com.management.hotel.reservation.dto.HotelManagementDto;
import com.management.hotel.reservation.dto.PaymentDto;
import com.management.hotel.reservation.exception.NoRoomsAvailableException;
import com.management.hotel.reservation.model.Reservation;
import com.management.hotel.reservation.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private ManagementClient managementClient;

    public Reservation save(Reservation reservation) {
        Boolean isRoomAvailable = managementClient.isRoomAvailable(reservation.getHotelId(), reservation.getRoomsToReserve());
        log.info("Response from Management Service, Is Room available ?:: {}" , isRoomAvailable);
        if(isRoomAvailable) makeReservationAndCancellation(reservation, "reserve");
        else{
            throw new NoRoomsAvailableException();
        }
        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    public String cancel(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()){
            makeReservationAndCancellation(reservation.get(), "cancel");
        }
        reservationRepository.deleteById(id);
        return "Reservation cancelled Successfully";
    }

    private void makeReservationAndCancellation(Reservation reservation, String action) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(new BigDecimal(reservation.getRoomsToReserve()*50));
        paymentDto.setCustomerId(reservation.getCustomerId());
        paymentDto.setMethod("Credit Card");
        PaymentDto paymentRequest = paymentClient.makePayment(paymentDto);
        ResponseEntity<PaymentDto> paymentResponse = paymentClient.getPaymentById(paymentRequest.getId());
        log.info("Response from Payment Service:: {}, {}" ,paymentResponse.getStatusCode(), paymentResponse);
        if(paymentResponse.getStatusCode().is2xxSuccessful()){
            HotelManagementDto hotelManagementDto =new HotelManagementDto();
            hotelManagementDto.setHotelId(reservation.getHotelId());
            hotelManagementDto.setRooms(reservation.getRoomsToReserve());
            hotelManagementDto.setAction("reserve");
            HotelManagementDto hotelManagementDtoResponse = managementClient.updateRooms(hotelManagementDto);
            log.info("Hotel rooms updated Successfully {}", hotelManagementDtoResponse);
        }
    }
}

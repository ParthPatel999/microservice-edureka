package com.management.hotel.reservation.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class HotelManagementDto {

    private Long hotelId;

    private Long rooms;

    private Boolean roomsAvailable;

    private String action;
}

package com.management.hotel.management.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "hotelmanagement")
public class HotelManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    @Column(name = "rooms", nullable = false)
    private Long rooms;

    @Column(name = "roomsAvailable")
    private Boolean roomsAvailable;

    private String action;

}

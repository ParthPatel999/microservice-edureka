package com.management.hotel.management.controller;

import com.management.hotel.management.model.HotelManagement;
import com.management.hotel.management.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/managements")
public class ManagementController {

    @Autowired
    private ManagementService managementService;

    @PostMapping
    public ResponseEntity<HotelManagement> addRooms(@RequestBody HotelManagement hotelManagement) {
        return ResponseEntity.ok(managementService.save(hotelManagement));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelManagement> getRoomsByHotelId(@PathVariable Long id) {
        return ResponseEntity.ok(managementService.findById(id).get());
    }

    @GetMapping("rooms/{id}/{roomsToReserve}")
    public ResponseEntity<Boolean> isRoomAvailable(@PathVariable Long id, @PathVariable Long roomsToReserve) {
        return ResponseEntity.ok(managementService.isRoomAvailable(id, roomsToReserve));
    }

    @PutMapping
    public ResponseEntity<HotelManagement> updateRooms(@RequestBody HotelManagement hotelManagement) {
        return ResponseEntity.ok(managementService.updateRooms(hotelManagement));
    }


}

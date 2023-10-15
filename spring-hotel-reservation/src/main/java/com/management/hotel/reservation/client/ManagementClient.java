package com.management.hotel.reservation.client;

import com.management.hotel.reservation.dto.HotelManagementDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "management-service", url = "http://localhost:8094", path = "/api/v1/managements")
public interface ManagementClient {

    @GetMapping("/rooms/{id}/{roomsToReserve}")
    public Boolean isRoomAvailable(@PathVariable("id") Long id, @PathVariable("roomsToReserve") Long roomsToReserve);

    @PutMapping
    public HotelManagementDto updateRooms(@RequestBody HotelManagementDto hotelManagementDto);
}

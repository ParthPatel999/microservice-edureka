package com.management.hotel.management.service;

import com.management.hotel.management.exception.NoEnoughRoomsAvailableException;
import com.management.hotel.management.model.HotelManagement;
import com.management.hotel.management.repository.HotelManagementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ManagementService {

    @Autowired
    private HotelManagementRepository hotelManagementRepository;
    public HotelManagement save(HotelManagement hotelManagement) {
        return hotelManagementRepository.save(hotelManagement);
    }

    public Optional<HotelManagement> findById(Long id) {
        return hotelManagementRepository.findById(id);
    }

    public Boolean isRoomAvailable(Long id, Long roomsToReserve) {
        Optional<HotelManagement> hotelManagement = hotelManagementRepository.findById(id);
        if(hotelManagement.isPresent()){
            if(hotelManagement.get().getRooms()-roomsToReserve > 0){
                return true;
            }else {
                return false;
            }
        }
        return null;
    }

    public HotelManagement updateRooms(HotelManagement hotelManagement) {
        log.info("Rooms to update:: {}", hotelManagement);
        Optional<HotelManagement> savedHotelManagement= hotelManagementRepository.findById(hotelManagement.getHotelId());
        if(savedHotelManagement.isPresent() && hotelManagement.getAction().equalsIgnoreCase("reserve")){
            HotelManagement updatedHotelManagement = new HotelManagement();
            updatedHotelManagement.setHotelId(savedHotelManagement.get().getHotelId());
            if(savedHotelManagement.get().getRooms() - hotelManagement.getRooms() > 0){
                updatedHotelManagement.setRoomsAvailable(true);
                updatedHotelManagement.setRooms(savedHotelManagement.get().getRooms() - hotelManagement.getRooms());
            }else{
                throw new NoEnoughRoomsAvailableException();
            }
            return hotelManagementRepository.save(updatedHotelManagement);
        } else if (savedHotelManagement.isPresent() && hotelManagement.getAction().equalsIgnoreCase("cancel")) {
            HotelManagement updatedHotelManagement = new HotelManagement();
            updatedHotelManagement.setHotelId(savedHotelManagement.get().getHotelId());
            updatedHotelManagement.setRoomsAvailable(true);
            updatedHotelManagement.setRooms(savedHotelManagement.get().getRooms() + hotelManagement.getRooms());
            return hotelManagementRepository.save(updatedHotelManagement);
        }
        return null;
    }
}

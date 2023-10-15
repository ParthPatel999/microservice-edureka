package com.management.hotel.management.repository;

import com.management.hotel.management.model.HotelManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelManagementRepository extends JpaRepository<HotelManagement ,Long> {
}

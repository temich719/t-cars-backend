package com.tcars.maincarsservice.dao.repository;

import com.tcars.maincarsservice.dao.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarDAO extends JpaRepository<Car, UUID> {
}

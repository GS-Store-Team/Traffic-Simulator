package com.traffic_simulator.repository;

import com.traffic_simulator.models.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
}

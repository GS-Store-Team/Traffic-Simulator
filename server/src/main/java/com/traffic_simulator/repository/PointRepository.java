package com.traffic_simulator.repository;

import com.traffic_simulator.models.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}

package com.traffic_simulator.repository;

import com.traffic_simulator.models.AreaVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface AreaVersionRepository extends JpaRepository<AreaVersion, Long> {
    public List<AreaVersion> findAreaVersionsByUserUsername(String username);
}

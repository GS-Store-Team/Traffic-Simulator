package com.traffic_simulator.services;

import com.traffic_simulator.models.AreaVersion;
import com.traffic_simulator.repository.AreaVersionRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AreaVersionService {
    private final AreaVersionRepository areaVersionRepository;

    public AreaVersionService(AreaVersionRepository areaVersionRepository) {
        this.areaVersionRepository = areaVersionRepository;
    }

    public void test(){
        System.out.println("HERE");

        AreaVersion areaVersion = new AreaVersion();
        areaVersion.setLabel("Area 001");
        areaVersion.setCreated(new Timestamp(System.currentTimeMillis()));
        areaVersion.setEdited(new Timestamp(System.currentTimeMillis()));

        System.out.println("HERE1");
        areaVersionRepository.save(areaVersion);
        System.out.println("HERE2");
    }
}

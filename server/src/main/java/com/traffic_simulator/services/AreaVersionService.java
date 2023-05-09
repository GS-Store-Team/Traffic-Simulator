package com.traffic_simulator.services;

import com.traffic_simulator.dto.AreaDTO;
import com.traffic_simulator.dto.FullMapDTO;
import com.traffic_simulator.models.Area;
import com.traffic_simulator.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AreaVersionService {
    private final AreaRepository areaRepository;

    public FullMapDTO getState(){
        List<Area> areas = areaRepository.findAll();

        return new FullMapDTO(
                0L,
                areas.stream().map(a -> new AreaDTO(a, a.getVersions(), true)).collect(Collectors.toList())
        );
    }
}

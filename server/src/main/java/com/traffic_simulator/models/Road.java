package com.traffic_simulator.models;

import com.traffic_simulator.dto.RoadDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Road {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "areaVersion_id")
    private AreaVersion areaVersion;
    private Long forward;
    private Long reverse;
    @OneToOne
    private Point start;
    @OneToOne
    private Point end;
}

package com.traffic_simulator.models;

import com.traffic_simulator.enums.BuildingType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Building {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "areaVersion_id")
    private AreaVersion areaVersion;
    @OneToOne
    private Point location;
    private Double inFlow;
    private Double outFlow;
    private BuildingType type;
    private String label;
    @OneToOne
    private Parking parking;
}

package com.traffic_simulator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Area {
    @Id
    @GeneratedValue
    private Long id;
    private String label;
    @OneToMany(mappedBy = "area")
    private List<AreaVersion> versions;
}

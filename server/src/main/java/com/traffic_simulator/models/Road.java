package com.traffic_simulator.models;

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
    @OneToOne(cascade = {CascadeType.ALL})
    private Point start;
    @OneToOne(cascade = {CascadeType.ALL})
    private Point end;
}

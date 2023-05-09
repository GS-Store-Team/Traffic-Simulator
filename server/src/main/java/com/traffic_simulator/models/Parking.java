package com.traffic_simulator.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Parking {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Building building;
    private Long capacity;
    @OneToOne
    private Point location;
}

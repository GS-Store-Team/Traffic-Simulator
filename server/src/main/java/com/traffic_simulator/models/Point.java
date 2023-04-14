package com.traffic_simulator.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Point {
    @Id
    @GeneratedValue
    private Long id;
    private Double x;
    private Double y;
}

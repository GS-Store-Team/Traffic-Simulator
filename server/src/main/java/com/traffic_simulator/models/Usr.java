package com.traffic_simulator.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Usr {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    @OneToMany(mappedBy = "usr")
    private List<AreaVersion> areaVersions;
}

package com.traffic_simulator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
public class AreaVersion {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usr_id")
    private Usr usr;
    private Boolean locked;
    private Timestamp created;
    private Timestamp edited;
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;
    @OneToMany(mappedBy = "areaVersion")
    private List<Building> buildings;
    @OneToMany(mappedBy = "areaVersion")
    private List<Road> roads;
    private String label;
}

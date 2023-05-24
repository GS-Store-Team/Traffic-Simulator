package com.traffic_simulator.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

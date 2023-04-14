package com.traffic_simulator.models;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UserData {
    private User usr;
    private List<Area> areas;
}

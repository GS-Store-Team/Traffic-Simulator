package com.traffic_simulator.businnes_logic;

import com.traffic_simulator.businnes_logic.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.businnes_logic.models.buildings.Building;
import com.traffic_simulator.businnes_logic.models.road.Road;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class RoadMap {
    private String name;
    private String description;
    private List<String> authors;
    private Date creationDate;
    private Date lastEditDate;

    private Coordinates center;
    private List<AttachmentPoint> attachmentPoints;
    private List<Road> roads;
    private List<Building> buildings;
}

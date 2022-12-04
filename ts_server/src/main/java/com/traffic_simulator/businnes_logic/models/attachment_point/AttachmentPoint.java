package com.traffic_simulator.businnes_logic.models.attachment_point;

import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AttachmentPoint extends GraphObject {
    private Coordinates coordinates;
    public AttachmentPoint(Coordinates coordinates) {
        super();
        this.coordinates = coordinates;
    }
}

package com.traffic_simulator.businnes_logic.models.road;

import com.traffic_simulator.businnes_logic.models.attachment_point.AttachmentPoint;
import com.traffic_simulator.businnes_logic.models.GraphObject;
import com.traffic_simulator.businnes_logic.models.supportive.Coordinates;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Road extends GraphObject {
    private AttachmentPoint startPoint;
    private AttachmentPoint endPoint;
    private List<Lane> rightLanes;
    private List<Lane> leftLanes;

    public Road(Coordinates startCoordinates, Coordinates endCoordinates, int rightLanesAmount, int leftLanesAmount) {
        super();

        this.startPoint = new AttachmentPoint(startCoordinates);
        this.endPoint = new AttachmentPoint(endCoordinates);
        this.rightLanes = new ArrayList<>();
        this.leftLanes = new ArrayList<>();
        addLanes(rightLanesAmount, leftLanesAmount);

        this.naturalWeight = computeNaturalWeightByCoordinates();
    }

    public void addLanes(int rightLanesAmount, int leftLanesAmount) {
        for (int i = 0; i < rightLanesAmount; i++) {
            rightLanes.add(new Lane(
                    startPoint.getCoordinates(),
                    endPoint.getCoordinates()));
        }

        for (int i = 0; i < leftLanesAmount; i++) {
            rightLanes.add(new Lane(
                    startPoint.getCoordinates(),
                    endPoint.getCoordinates()));
        }
    }

    private int computeNaturalWeightByCoordinates() {
        int weight = 0;

        @// TODO: 04.12.2022 Создать класс с единым форматом клеток. Это необходимо для корректного расчета их количества, веса дороги.


        return weight;
    }
}

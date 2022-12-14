package com.traffic_simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AttachmentPointDTO {
    private long id;
    private PointDTO location;
}
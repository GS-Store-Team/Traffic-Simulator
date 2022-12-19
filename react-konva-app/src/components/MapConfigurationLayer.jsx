import React from 'react';
import {Circle, Group, Layer, Line, Rect} from "react-konva";

export const MapConfigurationLayer = ({config}) => {
    return (
        <Layer>
            {config.buildings.map((b) =>
                <Rect key={b.id}
                      x={b.location.x}
                      y={b.location.y}
                      width={50}
                      height={50}
                      fill={"gray"}
                />)}
            {config.roads.map((r) =>
                <Group>
                    <Line x={0}
                          y={0}
                          points={[r.start.x, r.start.y, r.end.x, r.end.y]}
                          strokeWidth={10}
                          stroke={"gray"}/>
                    <Line x={0}
                          y={0}
                          points={[r.start.x, r.start.y, r.end.x, r.end.y]}
                          strokeWidth={0.2}
                          stroke={"white"}/>
                    <Circle key={-3}
                            x={r.end.x}
                            y={r.end.y}
                            fill={"gray"}
                            radius={4.9}/>
                    <Circle key={-4}
                            x={r.start.x}
                            y={r.start.y}
                            fill={"gray"}
                            radius={4.9}/>
                </Group>)}
        </Layer>
        );
};
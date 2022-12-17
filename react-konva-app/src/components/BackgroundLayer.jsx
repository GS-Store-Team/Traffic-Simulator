import React from 'react';
import {Circle, Group, Layer, Line, Rect} from "react-konva";

export const BackgroundLayer = ({config}) => {
    return (
        <Layer>
            {config.buildings.map((b) =>
                <Rect x={b.location.x}
                       y={b.location.y}
                       width={50}
                       height={50}
                       fill={"gray"}
                />)}
            {config.roads.map((r) =>
                <Group>
                    <Circle x={r.start.x}
                            y={r.start.y}
                            fill={"gray"}
                            radius={5}/>
                    <Line
                        x={0}
                        y={0}
                        points={[r.start.x, r.start.y, r.end.x, r.end.y]}
                        strokeWidth={10}
                        stroke={"gray"}/>
                    <Circle x={r.end.x}
                            y={r.end.y}
                            fill={"gray"}
                            radius={5}/>
                </Group>)}
        </Layer>
        );
};
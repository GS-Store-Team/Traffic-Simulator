import React from 'react';
import {Group, Layer, Line, Rect} from "react-konva";

export const BackgroundLayer = ({config}) => {

    const building = {x:100, y:200, width:100,height:100};


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
                <Line
                    x={0}
                    y={0}
                    points={[r.start.x, r.start.y, r.end.x, r.end.y]}
                    strokeWidth={10}
                    stroke={"gray"}
                />)}
        </Layer>
        );
};
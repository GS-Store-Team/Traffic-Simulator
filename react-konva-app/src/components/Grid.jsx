import React from 'react';
import {Group, Line} from "react-konva";

export const Grid = ({width, height, scale}) => {
    return (
        <Group>
            <Line x={0}
                  y={0}
                  points={[width/2 - 10, height/2, width/2 + 10, height/2]}
                  strokeWidth={1/scale}
                  stroke={"black"}
                  />
            <Line x={0}
                  y={0}
                  points={[width/2, height/2 - 10, width/2, height/2 + 10]}
                  strokeWidth={1/scale}
                  stroke={"black"}
            />
        </Group>
    );
};
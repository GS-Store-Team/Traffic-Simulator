import React from 'react';
import {Circle} from "react-konva";
import {mapCell} from "../utils/Utils.js";

export const Grid = () => {

    const grid = [];

    for(let i = 0; i + mapCell < 860; i+=mapCell)
        for(let j = 0; j + mapCell < 1940; j+=mapCell)
            grid.push(
                <Circle
                        x = {j}
                        y={i}
                        radius={0.4}
                        fill = {"black"}
                />
            )

    return (
        <div>
            {grid}
        </div>
    );
};
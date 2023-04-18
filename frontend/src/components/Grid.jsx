import React, {useContext, useEffect, useState} from 'react';
import {Group, Line} from "react-konva";
import {Context} from "../router/AppRouter.jsx";

export const Grid = ({width, height}) => {
    const {scale} = useContext(Context);
    const [myScale, setMyScale] = useState(1);
    useEffect(() => {
        scale<0.8? setMyScale(0.8): setMyScale(scale);
    }, [scale])

    return (
        <Group>
            <Line x={0}
                  y={0}
                  points={[width/2 - 10, height/2, width/2 + 10, height/2]}
                  strokeWidth={1/myScale}
                  stroke={"black"}
                  />
            <Line x={0}
                  y={0}
                  points={[width/2, height/2 - 10, width/2, height/2 + 10]}
                  strokeWidth={1/myScale}
                  stroke={"black"}
            />
        </Group>
    );
};
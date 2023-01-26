import React, {useContext, useEffect, useState} from 'react';
import {Layer, Line} from "react-konva";
import {Context} from "../router/AppRouter.jsx";

export const PhonyObjects = ({road, building, mouse, stage}) => {

    const {scale, shift} = useContext(Context);

    const [mousePos, setMousePos] = useState({});

    useEffect(() => {
        const handleMouseMove = (event) => {
            setMousePos({ x: event.clientX, y: event.clientY });
        };

        window.addEventListener('mousemove', handleMouseMove);

        return () => {
            window.removeEventListener(
                'mousemove',
                handleMouseMove
            );
        };
    }, []);


    const road1 = <Line
                  x={0}
                  y={0}
                  points={[0, 0, 100, 0]}
                  strokeWidth={12}
                  stroke={"black"}
                  />
    return (
        <Layer
            x={mousePos.x/scale/scale + shift.x}
            y={mousePos.y/scale/scale + (shift.y -100)}
        >
            {road1}
        </Layer>
    );
};
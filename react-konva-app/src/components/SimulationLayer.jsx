import React, {useEffect, useState} from 'react';
import {Layer} from "react-konva";
import API from "../API.js";
import {SimulationFrame} from "./SimulationFrame";

export const SimulationLayer = ({started, stopped, frames}) => {

    const [currentFrame, setCurrentFrame] = useState()

    useEffect(() => {
        if(started && !stopped) {
            let interval = setInterval(() => {
                API.startSimulation().then((response) => {
                    if (response.status === 200) {
                        console.log("frame");
                        setCurrentFrame(<SimulationFrame data ={response.data} />)
                    }
                });
            }, 1000 / frames);
            return () => {
                clearInterval(interval);
            };
        }
    }, [started, stopped, frames]);

    return (
        <Layer>
            {currentFrame}
        </Layer>
    );
};
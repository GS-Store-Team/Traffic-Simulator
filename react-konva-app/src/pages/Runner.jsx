import React, {useEffect, useState} from 'react';
import {Header} from "../components/header/Header";
import {RunnerToolbar} from "../components/RunnerToolbar/RunnerToolbar";
import API from "../API.js";
import {Stage} from "react-konva";
import {BackgroundLayer} from "../components/RunnerToolbar/BackgroundLayer";

export const Runner = () => {
    const [config, setConfig] = useState(undefined);
    const [state, setState] = useState(undefined);
    const [started, setStarted] = useState(false);

    useEffect(() =>{
        getConfig();
    }, [])

    const getConfig = () => {
        setStarted(false);
        API.getMapConfig().then((response) => {
            setConfig(response.data);
            console.log(config);
        }).catch((e) => {
            setConfig(undefined);
        })
    }

    const startSimulation = () => {
        API.startSimulation().then((response) => {
            if(response.status === 200) setStarted(true);
        })
    }

    const stopSimulation = () => {
        API.stopSimulation().then((response) => {
            if(response.status === 200) setStarted(true);
        })
    }

    const continueSimulation = () => {
        API.playSimulation().then((response) => {
            if(response.status === 200) {
            }
        })
    }

    const getState = () => {
        API.getMapState().then((response) => {
            console.log(response);
        }).catch((e) => {
            setState(undefined);
        })
    }

    return (
        <div>
            <Header state={true}/>
            <RunnerToolbar reload={getConfig} start={startSimulation} stop={stopSimulation} continuesim={continueSimulation} started={started}/>
            {config?
                <Stage
                    width={1920}
                    height={840}>
                    <BackgroundLayer config={config}/>
                </Stage>
                : <div> Cant fetch server..</div>
            }
        </div>
    );
};
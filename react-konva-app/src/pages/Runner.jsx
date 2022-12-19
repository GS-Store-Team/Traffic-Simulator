import React, {useEffect, useState} from 'react';
import {Header} from "../components/header/Header";
import {RunnerToolbar} from "../components/RunnerToolbar/RunnerToolbar";
import API from "../API.js";
import {MapConfigurationLayer} from "../components/MapConfigurationLayer.jsx";
import {MyStage} from "../components/mystage/MyStage.jsx";
import {SimulationLayer} from "../components/SimulationLayer.jsx";

export const Runner = () => {
    const [config, setConfig] = useState(undefined);
    const [state, setState] = useState(undefined);
    const [started, setStarted] = useState(false);
    const [stopped, setStopped] = useState(false);
    const [frames, setFrames] = useState(1);
    const [defaultVis, setDefaultVis] = useState(true);

    useEffect(() =>{
        getConfig();
    }, [])

    const getConfig = () => {
        setStarted(false);
        API.getMapConfig().then((response) => {
            setConfig(response.data);
            console.log("MAP CONFIGURATION:")
            console.log(response.data);
        }).catch((e) => {
            setConfig(undefined);
        })
    }

    const startSimulation = () => {
        setStopped(false);
        console.log("START");
        API.startSimulation().then((response) => {
            if(response.status === 200) setStarted(true);
        })
    }

    const stopSimulation = () => {
        setStopped(true);
        console.log("stopped");
        API.stopSimulation().then((response) => {
            //if(response.status === 200) setStarted(true);
        })
    }

    const continueSimulation = () => {
        setStopped(false);
        console.log("continued");
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
            <RunnerToolbar reload={getConfig}
                           start={startSimulation}
                           stop={stopSimulation}
                           continuesim={continueSimulation}
                           started={started}
                           setFrames={setFrames}
                           frames={frames}
                           setDefaultVis={setDefaultVis}/>
            {config?
                <MyStage layers={
                    [<MapConfigurationLayer key={0}
                                            config={config}/>,
                     <SimulationLayer key={1}
                                      stopped={stopped}
                                      started={started}
                                      frames={frames}/>]
                }/>
                : <div> Cant fetch server..</div>
            }
        </div>
    )
}
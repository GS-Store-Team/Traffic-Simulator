import React, {useState} from 'react';
import classes from "./runnertoolbar.module.css";
import API from "../../API.js";

export const RunnerToolbar = ({reload, start, stop, continuesim, started, setFrames, frames, setDefaultVis}) => {
    const [stopped, setStopped] = useState(false);

    const startSimulation = (e) =>{
        e.preventDefault();
        setStopped(false);
        start();
    }

    const reloadMap = (e) =>{
        e.preventDefault();
        reload();
    }

    const stopsim = (e) =>{
        e.preventDefault();
        setStopped(true);
        stop();
    }
    const contsim = (e) =>{
        e.preventDefault();
        setStopped(false);
        continuesim();
    }

    const [vis, setVis] = useState(true);

    const defaultVis = () =>{
        setVis(!vis);
        setDefaultVis(!vis);
    }

    return (
        <div className={classes.my__toolbar}>
            <div className={classes.my__frames}>
                <input onChange={(e) => setFrames(e.target.value)}
                       className={classes.my__slider}
                       type={"range"}
                       min={1}
                       max={10}
                       defaultValue={1}/>
                <div className={classes.my__frames__title}>{frames} fps</div>
            </div>

            <div className={classes.my__choice__box} onClick={defaultVis}>{vis ? "cars": "heat map"}</div>

            <button onClick={(e) => reloadMap(e)}
                    className={classes.my__button__reload}>Reload map</button>
            <button className={classes.my__button}
                    onClick={(e) => startSimulation(e)}>Start</button>
            <button disabled={!started || stopped}
                    className={classes.my__button}
                    onClick={(e) => stopsim(e)}>Stop</button>
            <button disabled={!started || !stopped}
                    className={classes.my__button}
                    onClick={(e) => contsim(e)}>Continue</button>
        </div>
    );
};
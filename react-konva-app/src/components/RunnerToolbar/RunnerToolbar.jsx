import React, {useState} from 'react';
import classes from "./toolbar.module.css";
import API from "../../API.js";

export const RunnerToolbar = ({reload, start, stop, continuesim, started}) => {
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

    return (
        <div className={classes.my__toolbar}>
            <button onClick={(e) => reloadMap(e)}
                    className={classes.my__button}>Reload map</button>
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
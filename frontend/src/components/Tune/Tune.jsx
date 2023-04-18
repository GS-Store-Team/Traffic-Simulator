import React, {useContext} from 'react';
import classes from "./tune.module.css";
import {Context} from "../../router/AppRouter.jsx";
import {RoadTune} from "./RoadTune.jsx";
import {BuildingTune} from "./BuildingTune.jsx";

export const Tune = () => {
    const {roadSettings, buildingSettings, setRoadSettings, setBuildingSettings} = useContext(Context);
    const deleteBuilding = () => {
        buildingSettings.delete()
        setBuildingSettings(null)
    }

    const deleteRoad = () => {
        console.log(roadSettings)
        roadSettings.delete()
        setRoadSettings(null)
    }

    return (
        <div>
            {roadSettings !== null ?
                <div className={classes.background}
                     onClick={() => setRoadSettings(null)}>
                    <div className={classes.tune}
                         onClick={e => e.stopPropagation()}>
                        <div className={classes.my__delete} onClick={deleteRoad}>delete</div>
                        <RoadTune roadSettings={roadSettings}/>
                    </div>
                </div>
                : null}
            {buildingSettings !== null ?
                <div className={classes.background}
                     onClick={() => setBuildingSettings(null)}>
                    <div className={classes.tune}
                         onClick={e => e.stopPropagation()}>
                        <div className={classes.my__delete} onClick={deleteBuilding}>delete</div>
                        <BuildingTune />
                    </div>
                </div>
                : null}
        </div>
    );
};
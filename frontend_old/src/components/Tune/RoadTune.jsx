import React, {useEffect, useState} from 'react';
import classes from "./tune.module.css";
import {MyChoiceBox} from "../mychoicebox/MyChoiceBox.jsx";

export const RoadTune = ({roadSettings}) => {
    const [forward, setForward] = useState(roadSettings.road.forwardLanesCnt)
    const [reverse, setReverse] = useState(roadSettings.road.reverseLanesCnt)

    useEffect(() => {
        roadSettings.setRoad({...roadSettings.road, forwardLanesCnt:forward, reverseLanesCnt:reverse})
    }, [forward, reverse])


    return (
        <div>
            <div className={classes.id}>
                ID: {roadSettings.road.id}
            </div>
            <div className={classes.text_block}>
                Forward Lanes
                <MyChoiceBox arr={[1,2,3,4]} current={forward} setCurrent={setForward}/>
            </div>

            <div className={classes.text_block}>
                Reverse Lanes
                <MyChoiceBox arr={[1,2,3,4]} current={reverse} setCurrent={setReverse}/>
            </div>
        </div>
    );
};
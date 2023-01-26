import React, {useEffect, useState} from 'react';
import classes from "./editortoolbar.module.css";
import settings from "../../UI/images/settings.png";
import {MyChoiceBox} from "../mychoicebox/MyChoiceBox.jsx";

export const DefaultObjConfig = ({conf}) => {
    const [visible,setVisible] = useState(false)
    const [forward, setForward] = useState(conf.defRoad.forwardLanesCnt)
    const [reverse, setReverse] = useState(conf.defRoad.reverseLanesCnt)

    useEffect(() =>{
        conf.setDefRoad({... conf.defRoad, forwardLanesCnt:forward, reverseLanesCnt: reverse})
    }, [forward, reverse])

    return (
        <div className={classes.settings}>
            <img className={classes.settings__img} src={settings} alt={".."} onClick={() => setVisible(true)}/>
            {visible?
                <div className={classes.background} onClick={() => setVisible(false)}>
                    <div className={classes.road__box} onClick={e => e.stopPropagation()}>
                        <div className={classes.settings__box__title}>Default Road</div>

                        <div className={classes.block}>
                            <div>Forward lanes</div>
                            <MyChoiceBox current={forward} arr={[1,2,3,4]} setCurrent={setForward}/>
                        </div>

                        <div className={classes.block}>
                            <div>Reverse lanes</div>
                            <MyChoiceBox current={reverse} arr={[1,2,3,4]} setCurrent={setReverse}/>
                        </div>

                    </div>

                </div>
                :<div/>}
        </div>
    );
};
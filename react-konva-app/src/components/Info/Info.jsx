import React, {useContext} from 'react';
import {Context} from "../../router/AppRouter.jsx";
import classes from "./info.module.css";

export const Info = () => {
    const {road, building} = useContext(Context);

    return (
        <div>
            {road !== null?
                <div className={classes.item}>
                    <div className={classes.my__coords}>
                        START x:{road.position.start.x} y:{road.position.start.y}
                    </div>
                    <div className={classes.my__coords1}>
                        END x:{road.position.end.x} y:{road.position.end.y}
                    </div>

                    <div className={classes.id}>
                        ID: {road.road.id}
                    </div>

                    <div className={classes.main__info}>
                        <div className={classes.row}>
                            Forward lanes: {road.road.forwardLanesCnt}
                        </div>
                        <div className={classes.row}>
                            Reverse lanes: {road.road.reverseLanesCnt}
                        </div>

                    </div>
                </div>
            :null}
            {building !== null?
                <div className={classes.item}>
                    <div className={classes.my__coords}>
                        UP LEFT x:{building.position.x} y:{building.position.y}
                    </div>

                    <div className={classes.id}>
                        ID: {building.building.id}
                    </div>

                    {/*<div className={classes.main__info}>*/}
                    {/*    <div className={classes.row}>*/}
                    {/*        Forward lanes: {road.road.forwardLanesCnt}*/}
                    {/*    </div>*/}
                    {/*    <div className={classes.row}>*/}
                    {/*        Reverse lanes: {road.road.reverseLanesCnt}*/}
                    {/*    </div>*/}

                    {/*</div>*/}
                </div>
                :null}
        </div>
    );
};
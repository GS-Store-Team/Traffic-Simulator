import React, {useContext, useEffect, useState} from 'react';
import {Circle, Group, Rect} from "react-konva";
import {ceilPosition} from "../utils/Utils";
import {Context} from "../router/AppRouter.jsx";
import {MultiLanesRoad} from "./MultiLanesRoad.jsx";
import API from "../API.js";
export const Road = (props) => {
    const [road, setRS] = useState(props.road);
    const {scale, setRoad, setRoadSettings} = useContext(Context);
    const [myScale, setMyScale] = useState(1);
    useEffect(() => {
        scale<0.8? setMyScale(0.8): setMyScale(scale);
    }, [scale])

    const [visible, setVisible] = useState(false);
    const [roadState, setRoadState] = useState({
        start:{x:road.x, y:road.y},
        end:{x:road.x1, y:road.y1},
    })

    useEffect(()=>{
        if(visible) setRoad({road:road, position:roadState})
        else setRoad(null);
    }, [visible, roadState])

    useEffect(()=>{
        // API.sendRoad(
        //     {
        //         id:road.id,
        //         start:roadState.start,
        //         end:roadState.end,
        //         forwardLanesCnt: road.forwardLanesCnt,
        //         reverseLanesCnt:road.reverseLanesCnt,
        //         forwardRoadSigns: null,
        //         reverseRoadSigns: null,
        //     }).then(response => {
        //         console.log(response);
        // })

        API.sendRoad(
            {
                id:0,
                start:{x:0, y:0},
                end:{x:0, y:0},
                forwardLanesCnt:1,
                reverseLanesCnt:1,
                //offsetX:offsetX,
                //offsetY:offsetY,
            }).then(response => {
            console.log(response);
        })
    }, [roadState, road])

    const remove = () =>{
        setVisible(false)
        props.rm(road);
    }

    const circleStart = <Circle
        x={road.x}
        y={road.y}
        fill={visible ? "green":"gray"}
        radius={road.pointRadius/myScale}
        draggable={true}
        onDragMove={(e) =>changerStart(e)}
        onDragEnd={(e) => setRoadState({...roadState,
            start:{x:e.target.attrs.x+e.target.parent.attrs.x,
                y:e.target.attrs.y+e.target.parent.attrs.y},
        })}
    />

    const circleEnd = <Circle
        x={road.x1}
        y={road.y1}
        fill={road.pointFill}
        radius={road.pointRadius/myScale}
        draggable={true}
        onDragMove={(e) =>changerEnd(e)}
        onDragEnd={(e) => setRoadState({...roadState,
            end:{x:e.target.attrs.x+e.target.parent.attrs.x,
                y:e.target.attrs.y+e.target.parent.attrs.y},
        })}
    />

    const [startX, setStartX] = useState(road.x);
    const [startY, setStartY] = useState(road.y);
    const [endX, setEndX] = useState(road.x1);
    const [endY, setEndY] = useState(road.y);
    const changerStart = (e) => {
        ceilPosition(e);
        setStartX(e.target.attrs.x);
        setStartY(e.target.attrs.y);
    }

    const changerEnd = (e) => {
        ceilPosition(e);
        setEndX(e.target.attrs.x);
        setEndY(e.target.attrs.y);
    }

    const changerGroup = (e) => {
        if(e.target.constructor.name === "Group")
            setRoadState({...roadState,
                start:{x:e.target.attrs.x+startX,
                    y:e.target.attrs.y+startY},
                end:{x:e.target.attrs.x+endX,
                    y:e.target.attrs.y+endY},
            });
    }

    const enterRoad = () => {
        setVisible(true);
    }

    const leaveRoad = () => {
        setVisible(false);
    }

    const changeSettings = () =>{
        setRoadSettings({road:road, delete:remove, setRoad:setRS})
    }

    const multiRoad = <MultiLanesRoad position={{start:{x:startX, y:startY}, end:{x:endX,y:endY}}} reverse={road.reverseLanesCnt} forward={road.forwardLanesCnt} enterRoad={enterRoad} leaveRoad={leaveRoad} changeSettings={changeSettings}/>

    const group =   <Group draggable={true}
                    onDragMove={(e) => ceilPosition(e)}
                    onDragEnd={(e) => changerGroup(e)}>
                    {multiRoad}
                    {circleStart}
                    {circleEnd}
                    </Group>

    return (
        group
    );
};
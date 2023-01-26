import React, {useEffect, useState} from 'react';
import {Group, Line} from "react-konva";
import {calcPointsForAdditionalLanes} from "../utils/Utils.js";

const roadShae = (forward, reverse, points) => {
    let p1;
    let p2;
    let p3 = {x:points.reverse.first.start.x, y:points.reverse.first.start.y}
    let p4 = {x:points.reverse.first.end.x, y:points.reverse.first.end.y}

    switch (forward){
        default: p1 = {x:points.forward.first.start.x, y:points.forward.first.start.y}; p2 = {x:points.forward.first.end.x, y:points.forward.first.end.y}; break;
        case 2: p1 = {x:points.forward.second.start.x, y:points.forward.second.start.y}; p2 = {x:points.forward.second.end.x, y:points.forward.second.end.y}; break;
        case 3: p1 = {x:points.forward.third.start.x, y:points.forward.third.start.y}; p2 = {x:points.forward.third.end.x, y:points.forward.third.end.y}; break;
        case 4: p1 = {x:points.forward.fourth.start.x, y:points.forward.fourth.start.y}; p2 = {x:points.forward.fourth.end.x, y:points.forward.fourth.end.y}; break;
    }

    switch (reverse){
        default: p3 = {x:points.reverse.first.start.x, y:points.reverse.first.start.y}; p4 = {x:points.reverse.first.end.x, y:points.reverse.first.end.y}; break;
        case 2: p3 = {x:points.reverse.second.start.x, y:points.reverse.second.start.y}; p4 = {x:points.reverse.second.end.x, y:points.reverse.second.end.y}; break;
        case 3: p3 = {x:points.reverse.third.start.x, y:points.reverse.third.start.y}; p4 = {x:points.reverse.third.end.x, y:points.reverse.third.end.y}; break;
        case 4: p3 = {x:points.reverse.fourth.start.x, y:points.reverse.fourth.start.y}; p4 = {x:points.reverse.fourth.end.x, y:points.reverse.fourth.end.y}; break;
    }

    return ({p1,p2,p3,p4})
}

export const MultiLanesRoad = ({position, forward, reverse, enterRoad, leaveRoad, changeSettings}) => {

    const [visible, setVisible] = useState(false)

    useEffect(() => {
        if(visible) enterRoad()
        else leaveRoad()
    }, [visible])

    const points = calcPointsForAdditionalLanes(position);

    const shape = roadShae(forward, reverse, points)

    const road = <Line x={0} y={0}
                       points={[shape.p1.x, shape.p1.y,shape.p2.x, shape.p2.y,shape.p3.x, shape.p3.y,shape.p4.x, shape.p4.y]} stroke={"black"}
                       strokeWidth={0}
                       closed={true}
                       fill={visible? "gold":"gray"}
                       onMouseEnter={() => setVisible(true)}
                       onMouseLeave={() => setVisible(false)}
                       onClick={changeSettings}
    />

    const dash = [1,1]

    return (
        <Group>
            {road}
            <Line x={0} y={0} points={[position.start.x, position.start.y, position.end.x, position.end.y]} stroke={"white"} strokeWidth={0.2} onMouseEnter={() => setVisible(true)}/>

            {forward > 1? <Line x={0} y={0} points={[points.forward.first.start.x,points.forward.first.start.y,points.forward.first.end.x,points.forward.first.end.y]} stroke={"white"} strokeWidth={0.2} dash={dash} onMouseEnter={() => setVisible(true)}/>:null}
            {forward > 2? <Line x={0} y={0} points={[points.forward.second.start.x,points.forward.second.start.y,points.forward.second.end.x,points.forward.second.end.y]} stroke={"white"} strokeWidth={0.2} dash={dash} onMouseEnter={() => setVisible(true)}/>:null}
            {forward > 3? <Line x={0} y={0} points={[points.forward.third.start.x,points.forward.third.start.y,points.forward.third.end.x,points.forward.third.end.y]} stroke={"white"} strokeWidth={0.2} dash={dash} onMouseEnter={() => setVisible(true)}/>:null}

            {reverse > 1? <Line x={0} y={0} points={[points.reverse.first.start.x,points.reverse.first.start.y,points.reverse.first.end.x,points.reverse.first.end.y]} stroke={"white"} strokeWidth={0.2} dash={dash} onMouseEnter={() => setVisible(true)}/>:null}
            {reverse > 2? <Line x={0} y={0} points={[points.reverse.second.start.x,points.reverse.second.start.y,points.reverse.second.end.x,points.reverse.second.end.y]} stroke={"white"} strokeWidth={0.2} dash={dash} onMouseEnter={() => setVisible(true)}/>:null}
            {reverse > 3? <Line x={0} y={0} points={[points.reverse.third.start.x,points.reverse.third.start.y,points.reverse.third.end.x,points.reverse.third.end.y]} stroke={"white"} strokeWidth={0.2} dash={dash} onMouseEnter={() => setVisible(true)}/>:null}
        </Group>
    );
};
import React, {useState} from 'react';
import {Circle, Group, Line, Rect} from "react-konva";
import {ceilPosition, mapCoordinateCeil} from "./utils/Utils";
const Road = (props) => {

    const road = props.road;

    const [visible, setVisible] = useState(false);
    const [stroke, setStroke] = useState(false);

    const remove = () =>{
        props.rm(road);
    }

    const circleStart = <Circle
        x={road.x}
        y={road.y}
        fill={road.pointFill}
        radius={road.pointRadius}
        draggable={true}
        onDragMove={(e) =>changerStart(e)}
    />

    const circleEnd = <Circle
        x={road.x+road.offSet}
        y={road.y}
        fill={road.pointFill}
        radius={road.pointRadius}
        draggable={true}
        onDragMove={(e) =>changerEnd(e)}
    />

    const [startX, setStartX] = useState(road.x);
    const [startY, setStartY] = useState(road.y);
    const [endX, setEndX] = useState(road.x+road.offSet);
    const [endY, setEndY] = useState(road.y);

    const changerStart = (e) => {
        ceilPosition(e);
        setStartX(e.target.attrs.x);
        setStartY(e.target.attrs.y)
    }

    const changerEnd = (e) => {
        ceilPosition(e);
        setEndX(e.target.attrs.x);
        setEndY(e.target.attrs.y)
    }

    const fullSelect = () => {
        setVisible(true);
        setStroke(true);
        setExitStroke(true);
    }

    const drawArea = () => {
        setVisible(true);
    }
    const eraseArea = () => {
        setVisible(false);
        setStroke(false);
        setExitStroke(false);
    }

    const eraseStroke = () => {
        setStroke(false);
        setExitStroke(false);
    }

    const line = <Line
                x={0}
                y={0}
                points={[startX, startY, endX, endY]}
                // tension={0.5}
                strokeWidth={road.lineStroke}
                stroke={stroke?road.enter:road.lineFill}
                onMouseEnter={fullSelect}
                onMouseLeave={eraseStroke}
                />

    const area = <Rect
                x={startX <= endX ? startX-10: startX+10}
                y={startY <= endY ? startY-10: startY+10}
                width={(endX- startX) >= 0?endX- startX+20:endX- startX - 20}
                height={(endY- startY) >= 0?endY- startY+20:endY- startY- 20}
                strokeWidth={1}
                stroke={"black"}
                visible={visible}
                onMouseLeave={eraseArea}
                onMouseEnter={drawArea}
                />
    const [exitStroke, setExitStroke] = useState(false);

    const exit = <Circle
        x={startX <= endX ? startX + (endX-startX)*0.9: endX + (startX-endX)*0.9}
        y={startY <= endY ? startY-10: endY-10}
        fill={"red"}
        radius={8}
        visible={visible}
        onMouseEnter={fullSelect}
        onMouseLeave={eraseArea}
        strokeEnabled={exitStroke}
        strokeWidth={1}
        stroke={"black"}
        shadowBlur={3}
        shadowEnabled={exitStroke}
        onClick={remove}
    />

    const group =   <Group draggable={true}
                    onDragMove={(e) => ceilPosition(e)}>
                    {area}
                    {exit}
                    {line}
                    {circleStart}
                    {circleEnd}
                    </Group>

    return (
        group
    );
};

export default Road;
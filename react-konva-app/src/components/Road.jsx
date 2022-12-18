import React, {useContext, useEffect, useState} from 'react';
import {Circle, Group, Line, Rect} from "react-konva";
import {ceilPosition} from "../utils/Utils";
import {Context} from "../router/AppRouter.jsx";
const Road = (props) => {
    const road = props.road;

    const {scale} = useContext(Context);
    const [myScale, setMyScale] = useState(1);
    useEffect(() => {
        scale<0.8? setMyScale(0.8): setMyScale(scale);
    }, [scale])

    const [visible, setVisible] = useState(false);
    const [stroke, setStroke] = useState(false);
    const [roadState, setRoadState] = useState({
        //id:0,
        start:{x:0, y:0},
        end:{x:0, y:0},
        //forwardLanesCnt:1,
        //reverseLanesCnt:1,
        //offsetX:offsetX,
        //offsetY:offsetY,
    })

    useEffect(()=>{
        fetchNewRoad();
    },[roadState]);
    async function fetchNewRoad() {
        //API.sendRoad(roadState);
    }
    const remove = () =>{
        props.rm(road);
    }

    const circleStart = <Circle
        x={road.x}
        y={road.y}
        fill={road.pointFill}
        radius={road.pointRadius/myScale}
        draggable={true}
        onDragMove={(e) =>changerStart(e)}
        // onDragEnd={(e) => changerStartRoadState(e)}
    />

    const circleEnd = <Circle
        x={road.x+road.offSet}
        y={road.y}
        fill={road.pointFill}
        radius={road.pointRadius/myScale}
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
        setStartY(e.target.attrs.y);

    }

    const changerEnd = (e) => {
        ceilPosition(e);
        setEndX(e.target.attrs.x);
        setEndY(e.target.attrs.y);
    }

    const changerGroup = (e) => {
        setRoadState({...roadState,
            start:{x:e.target.attrs.x+startX,
                y:e.target.attrs.y+startY},
            end:{x:e.target.attrs.x+endX,
                y:e.target.attrs.y+endY},
        });
    }

    const fullSelect = () => {
        setVisible(true);
        setStroke(true);
        setExitStroke(true);
    }

    const drawArea = () => {
        setVisible(true);
    }

    const enterRoad = () => {
        setVisible(true);
        setStroke(true);
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
                strokeWidth={road.lineStroke}
                stroke={stroke?road.enter:road.lineFill}
                onMouseEnter={enterRoad}
                onMouseLeave={eraseStroke}
                shadowBlur={road.shadowBlur}
                />

    const separationLine = <Line
                                x={0}
                                y={0}
                                points={[startX, startY, endX, endY]}
                                strokeWidth={road.lineStroke/40}
                                stroke={"white"}
                            />

    const area = <Rect
                x={startX <= endX ? startX-10: startX+10}
                y={startY <= endY ? startY-10: startY+10}
                width={(endX- startX) >= 0?endX- startX+20:endX- startX - 20}
                height={(endY- startY) >= 0?endY- startY+20:endY- startY- 20}
                strokeWidth={0.2/myScale}
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
        radius={6/myScale}
        visible={visible}
        onMouseEnter={fullSelect}
        onMouseLeave={eraseArea}
        strokeEnabled={exitStroke}
        strokeWidth={0.5/myScale}
        stroke={"black"}
        shadowBlur={2/myScale}
        shadowEnabled={exitStroke}
        onClick={remove}
        opacity={exitStroke? 0.8:0.5}
    />

    const group =   <Group draggable={true}
                    onDragMove={(e) => ceilPosition(e)}
                    onDragEnd={changerGroup}>
                    {area}
                    {exit}
                    {line}
                    {separationLine}
                    {circleStart}
                    {circleEnd}
                    </Group>

    return (
        group
    );
};

export default Road;
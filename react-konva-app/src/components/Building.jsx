import React, {useContext, useEffect, useState} from 'react';
import {Circle, Rect, Group} from "react-konva";
import {ceilPosition} from "../utils/Utils";
import {Context} from "../router/AppRouter.jsx";
const Building = (props) => {

    const building = props.building

    const [visible, setVisible] = useState(false);
    const [stroke, setStroke] = useState(false);
    const [exitStroke, setExitStroke] = useState(false);

    const {scale} = useContext(Context);
    const [myScale, setMyScale] = useState(1);
    useEffect(() => {
        scale<0.8? setMyScale(0.8): setMyScale(scale);
    }, [scale])

    const drawArea = () => {
        setVisible(true);
    }

    const fullSelect = () => {
        setVisible(true);
        setStroke(true);
        setExitStroke(true);
    }

    const enterBuilding = () => {
        setVisible(true);
        setStroke(true);
    }

    const eraseArea = () => {
        setVisible(false);
        eraseStroke();
    }
    const eraseStroke = () => {
        setExitStroke(false);
        setStroke(false);
    }

    const remove = ()=>{
        props.rm(building);
    }

    const rect = <Rect
                    x={building.x}
                    y={building.y}
                    width={building.width}
                    height={building.height}
                    fill={stroke? building.enter: building.fill}
                    shadowBlur={building.shadowBlur}
                    onMouseEnter={enterBuilding}
                    onMouseLeave={eraseStroke}
                    />

    const area = <Circle
                        x={building.x+ 25}
                        y={building.y +25}
                        strokeWidth={0.4/myScale}
                        stroke={"black"}
                        radius={60}
                        visible={visible}
                        onMouseLeave={eraseArea}
                        onMouseEnter={drawArea}
                    />

    const exit = <Circle
                    x={building.x + 67}
                    y={building.y-17.5}
                    fill={"red"}
                    strokeWidth={0.5/myScale}
                    stroke={"black"}
                    radius={6/myScale}
                    visible={visible}
                    onMouseEnter={fullSelect}
                    onMouseLeave={eraseArea}
                    onClick={remove}
                    shadowBlur={2/myScale}
                    shadowEnabled={exitStroke}
                    opacity={exitStroke? 0.8:0.5}
                />

    const move = (e) =>{
    }

    const group =   <Group
                        draggable={true}
                        onDragMove={(e) => ceilPosition(e)}
                        onDragEnd={(e) => move(e)}>
                        {area}
                        {exit}
                        {rect}
                    </Group>

    return (
        group
    );
}

export default Building;
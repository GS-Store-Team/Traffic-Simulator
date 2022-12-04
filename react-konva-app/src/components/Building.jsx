import React, {useState} from 'react';
import {Circle, Rect, Group} from "react-konva";
import {ceilPosition} from "./utils/Utils";
const Building = (props) => {

    const building = props.building

    const [visible, setVisible] = useState(false);
    const [stroke, setStroke] = useState(false);

    const drawArea = () => {
        setVisible(true);
    }

    const fullSelect = () => {
        setVisible(true);
        setStroke(true);
    }

    const eraseArea = () => {
        setVisible(false);
        eraseStroke();
    }
    const eraseStroke = () => {
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
                    onMouseEnter={fullSelect}
                    onMouseLeave={eraseStroke}
                    />

    const area = <Circle
                        x={25}
                        y={25}
                        strokeWidth={1}
                        stroke={"black"}
                        radius={60}
                        visible={visible}
                        onMouseLeave={eraseArea}
                        onMouseEnter={drawArea}
                    />

    const exit = <Circle
                    x={66}
                    y={-16}
                    fill={"red"}
                    strokeWidth={1}
                    stroke={"black"}
                    radius={8}
                    visible={visible}
                    onMouseEnter={fullSelect}
                    onMouseLeave={eraseArea}
                    onClick={remove}
                    shadowBlur={3}
                    shadowEnabled={stroke}
                />

    const group =   <Group
                        draggable={true}
                        onDragMove={(e) => ceilPosition(e)}>
                        {area}
                        {exit}
                        {rect}
                    </Group>
    return (
        group
    );
}

export default Building;
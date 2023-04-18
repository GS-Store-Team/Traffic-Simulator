import React, {useContext, useEffect, useState} from 'react';
import {Circle, Group, Rect} from "react-konva";
import {ceilPosition} from "../utils/Utils";
import {Context} from "../router/AppRouter.jsx";

export const Building = (props) => {

    const building = props.building

    const [visible, setVisible] = useState(false);
    const [stroke, setStroke] = useState(false);
    const [buildingState, setBuildingState] = useState({x:building.x, y:building.y})
    const {scale, setBuilding, setBuildingSettings} = useContext(Context);
    const [myScale, setMyScale] = useState(1);

    useEffect(() => {
        scale<0.8? setMyScale(0.8): setMyScale(scale);
    }, [scale])

    useEffect(()=>{
        if(visible) setBuilding({building:building, position:buildingState})
        else setBuilding(null);
    }, [visible, buildingState])

    const enterBuilding = () => {
        setVisible(true);
        setStroke(true);
    }

    const eraseArea = () => {
        setVisible(false);
        setStroke(false)
    }
    const remove = ()=>{
        setVisible(false)
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
                    onMouseLeave={() => setStroke(false)}
                    onClick={() => setBuildingSettings({building:building, delete:remove})}
                    />

    const area = <Circle
                        x={building.x+ 25}
                        y={building.y +25}
                        strokeWidth={0.4/myScale}
                        stroke={"black"}
                        radius={60}
                        visible={visible}
                        onMouseLeave={eraseArea}
                    />

    const group =   <Group
                        draggable={true}
                        onDragMove={(e) => ceilPosition(e)}
                        onDragEnd={(e) => setBuildingState({x:e.target.attrs.x, y:e.target.attrs.y})}>
                        {area}
                        {rect}
                    </Group>

    return (
        group
    );
}
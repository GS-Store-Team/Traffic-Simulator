import {FC, useCallback, useContext, useState} from "react";
import {Circle, Group, Rect} from "react-konva";
import {BuildingDTO} from "../../api/rest-client";
import {ceilPosition} from "../../utils";
import {BUILDING_WIDTH} from "../../Constants";
import {ElementsConfigContext} from "../contexts/ElementsConfigProvider";
import {EditorContext} from "../../pages/Editor";
import {KonvaEventObject} from "konva/lib/Node";
import {restClient} from "../../api/axios.config";
import {StageContext} from "../../App";
import { Text } from 'react-konva';

interface BuildingProps{
    building: BuildingDTO
    readonly: boolean
}

const LABEL_WIDTH = 200

export const Building : FC<BuildingProps> = ({building, readonly}) => {
    const { configureBuilding, viewBuilding } = useContext(ElementsConfigContext);
    const { setMap, version } = useContext(EditorContext);
    const { scale } = useContext(StageContext);

    const [visible, setVisible] = useState(false);
    const [stroke, setStroke] = useState(false);

    const enterBuilding = useCallback(() => {
        setVisible(true);
        setStroke(true);
        viewBuilding(building)
    }, [building, viewBuilding])

    const leaveBuilding = useCallback(() => {
        setStroke(false)
        viewBuilding(undefined)
    }, [viewBuilding])

    const eraseArea = useCallback(() => {
        setVisible(false);
        setStroke(false)
    }, [])

    const handleClickBuilding = useCallback(() => configureBuilding(building), [building, configureBuilding])
    const handleDrag = useCallback((e: KonvaEventObject<DragEvent>) =>
        version && restClient
            .addBuilding(version.areaId, {...building, location:{id: building.location.id, x: Number.parseInt(e.target.attrs.x), y: Number.parseInt(e.target.attrs.y)}})
            .then(res => setMap(res, true))
        , [building, setMap, version])

    return(
        <Group draggable={!readonly}
               onDragMove={ceilPosition}
               onDragEnd={handleDrag}
        >
            <Text fontSize={14}
                  x={building.location.x + BUILDING_WIDTH / 2  - LABEL_WIDTH / 2}
                  y={building.location.y - 30}
                  text={building.label}
                  align={"center"}
                  width={LABEL_WIDTH}
            />
            <Circle
                x={building.location.x + BUILDING_WIDTH / 2}
                y={building.location.y + BUILDING_WIDTH / 2}
                strokeWidth={0.4 / scale}
                stroke={"black"}
                radius={60}
                visible={visible}
                onMouseLeave={eraseArea}
            />
            <Rect
                x={building.location.x}
                y={building.location.y}
                width={BUILDING_WIDTH}
                height={BUILDING_WIDTH}
                fill={stroke? "gold" : "gray"}
                onMouseEnter={enterBuilding}
                onMouseLeave={leaveBuilding}
                onClick={handleClickBuilding}
            />
        </Group>
    )
}
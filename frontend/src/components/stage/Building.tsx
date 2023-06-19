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

interface BuildingProps{
    building: BuildingDTO
    readonly: boolean
}

export const Building : FC<BuildingProps> = ({building, readonly}) => {
    const { setBuilding } = useContext(ElementsConfigContext);
    const { setMap, version } = useContext(EditorContext);
    const { scale } = useContext(StageContext);

    const [visible, setVisible] = useState(false);
    const [stroke, setStroke] = useState(false);

    const enterBuilding = useCallback(() => {
        setVisible(true);
        setStroke(true);
    }, [])

    const leaveBuilding = useCallback(() => setStroke(false), [])

    const eraseArea = useCallback(() => {
        setVisible(false);
        setStroke(false)
    }, [])

    const handleClickBuilding = useCallback(() => setBuilding(building), [building, setBuilding])
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
                shadowBlur={2}
                onMouseEnter={enterBuilding}
                onMouseLeave={leaveBuilding}
                onClick={handleClickBuilding}
            />
        </Group>
    )
}
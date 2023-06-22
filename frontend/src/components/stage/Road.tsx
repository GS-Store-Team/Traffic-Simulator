import {FC, useCallback, useContext, useMemo, useState} from "react";
import {Circle, Group} from "react-konva";
import {Coordinates, RoadDTO} from "../../api/rest-client";
import {ceilPosition} from "../../utils";
import {StageContext} from "../../App";
import {MultiLanesRoad} from "./MultiLanesRoad";
import {KonvaEventObject, Node} from "konva/lib/Node";
import {restClient} from "../../api/axios.config";
import {EditorContext} from "../../pages/Editor";
import {ElementsConfigContext} from "../contexts/ElementsConfigProvider";
import Konva from "konva";

interface RoadProps{
    road: RoadDTO
    readonly: boolean
}

function limitedScale(scale: number){
    if(scale > 3) {
        return 3
    }
    if(scale < 0.5){
        return 0.5
    }
    return scale
}

export const Road : FC<RoadProps> = ({road, readonly}) => {
    const { scale } = useContext(StageContext);
    const { setMap, version } = useContext(EditorContext);
    const { configureRoad, viewRoad, viewPoint } = useContext(ElementsConfigContext);

    const [visible, setVisible] = useState<boolean>(false);
    const [coords, setCoords] = useState<{start: Coordinates, end: Coordinates}>({start: road.start, end: road.end})

    const handleClick = useCallback(() => configureRoad(road), [configureRoad, road])

    const handleStartDragMove = useCallback((e: KonvaEventObject<DragEvent>) => {
        ceilPosition(e)
        setCoords({...coords, start:{x: e.target.attrs.x, y: e.target.attrs.y}})
    }, [coords])

    const handleStartDragEnd = useCallback((e: KonvaEventObject<DragEvent>) => {
        version && restClient
            .addRoad(version.id, {...road, start:{x: e.target.attrs.x, y: e.target.attrs.y}})
            .then(setMap)
    }, [road, setMap, version])

    const handleEndDragMove = useCallback((e: KonvaEventObject<DragEvent>) => {
        ceilPosition(e)
        setCoords({...coords, end:{x: e.target.attrs.x, y: e.target.attrs.y}})
    }, [coords])

    const handleEndDragEnd = useCallback((e: KonvaEventObject<DragEvent>) => {
        version && restClient
            .addRoad(version.id, {...road, end:{x: e.target.attrs.x, y: e.target.attrs.y}})
            .then(setMap)
    }, [road, setMap, version])

    const handleStartEnter = useCallback(() => viewPoint(road.start), [road.start, viewPoint])
    const handleEndEnter = useCallback(() => viewPoint(road.end), [road.end, viewPoint])
    const handlePointLeave = useCallback(() => viewPoint(undefined), [viewPoint])

    const handleRoadEnter = useCallback(() => {
        setVisible(true)
        viewRoad(road)
    }, [road, viewRoad])

    const handleRoadLeave = useCallback(() => {
        setVisible(false)
        viewRoad(undefined)
    }, [viewRoad])

    const handleRoadDrag = useCallback((e: KonvaEventObject<DragEvent>) => {
        // @ts-ignore
        const group = e.target as Konva.Group
        const start = group.children && group.children[1]
        const end = group.children && group.children[2]
        if(version && start && end) {
            restClient
                .addRoad(version.id, {
                    ...road,
                    start:{x: start.attrs.x + group.attrs.x, y: start.attrs.y + group.attrs.y},
                    end:{x: end.attrs.x + group.attrs.x, y: end.attrs.y + group.attrs.y}}
                ).then(setMap)
        }
    }, [road, setMap, version])


    const color = useMemo(() => road.valid ? "gray": "rgba(255,0,0,0.68)", [road.valid])

    return(
        <Group draggable={false}
               onDragMove={ceilPosition}
               onClick={handleClick}
               onDragEnd={handleRoadDrag}
               onMouseEnter={handleRoadEnter}
               onMouseLeave={handleRoadLeave}
        >
            <MultiLanesRoad start={coords.start}
                            end={coords.end}
                            reverse={road.reverse}
                            forward={road.forward}
                            fill={visible ? "gold" : color}
            />
            <Circle
                x={road.start.x}
                y={road.start.y}
                fill={"green"}
                radius={ 5 / limitedScale(scale)}
                draggable={!readonly}
                onDragMove={handleStartDragMove}
                onDragEnd={handleStartDragEnd}
                onMouseEnter={handleStartEnter}
                onMouseLeave={handlePointLeave}
            />
            <Circle
                x={road.end.x}
                y={road.end.y}
                fill={"gray"}
                radius={5 / limitedScale(scale)}
                draggable={!readonly}
                onDragMove={handleEndDragMove}
                onDragEnd={handleEndDragEnd}
                onMouseEnter={handleEndEnter}
                onMouseLeave={handlePointLeave}
            />
        </Group>
    )
}
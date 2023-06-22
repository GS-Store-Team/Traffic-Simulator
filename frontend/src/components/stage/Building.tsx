import React, {FC, useCallback, useContext, useMemo, useState} from "react";
import {Circle, Group, Rect, Text, Image} from "react-konva";
import {BuildingDTO} from "../../api/rest-client";
import {ceilPosition} from "../../utils";
import {BUILDING_ICON_SIZE, BUILDING_WIDTH} from "../../Constants";
import {ElementsConfigContext} from "../contexts/ElementsConfigProvider";
import {EditorContext} from "../../pages/Editor";
import {KonvaEventObject} from "konva/lib/Node";
import {restClient} from "../../api/axios.config";
import {StageContext} from "../../App";
import entertainment from "../../ui/entertainment.png"
import shop from "../../ui/shop.png"
import work from "../../ui/work.png"
import home from "../../ui/home.png"
import useImage from "use-image";

interface BuildingProps{
    building: BuildingDTO
    readonly: boolean
}

const LABEL_WIDTH = 200

export const Building : FC<BuildingProps> = ({building, readonly}) => {
    const { configureBuilding, viewBuilding, viewPoint } = useContext(ElementsConfigContext);
    const { setMap, version } = useContext(EditorContext);
    const { scale } = useContext(StageContext);

    const [visible, setVisible] = useState(false);
    const [stroke, setStroke] = useState(false);

    const enterBuilding = useCallback(() => {
        viewPoint(building.location)
        setVisible(true);
        setStroke(true);
        viewBuilding(building)
    }, [building, viewBuilding, viewPoint])

    const leaveBuilding = useCallback(() => {
        viewPoint(undefined)
        setStroke(false)
        viewBuilding(undefined)
    }, [viewBuilding, viewPoint])

    const eraseArea = useCallback(() => {
        setVisible(false);
        setStroke(false)
    }, [])

    const handleClickBuilding = useCallback(() => configureBuilding(building), [building, configureBuilding])
    const handleDrag = useCallback((e: KonvaEventObject<DragEvent>) => {
        version &&
            restClient
                .addBuilding(version.areaId, {...building, location:{id: building.location.id, x: e.target.attrs.x, y: e.target.attrs.y}})
                .then(setMap)
    }, [building, setMap, version])

    const [homeImg] = useImage(home);
    const [workImg] = useImage(work);
    const [shopImg] = useImage(shop);
    const [entertainmentImg] = useImage(entertainment);

    const buildingIcon = useMemo(() =>  {
        switch(building.type){
            case "LIVING": return homeImg
            case "WORKING": return workImg
            case "SHOP": return shopImg
            case "ENTERTAINMENT": return entertainmentImg
        }
    }, [building.type, entertainmentImg, homeImg, shopImg, workImg])

    const color = useMemo(() => building.valid ? "gray": "red", [building.valid])

    return(
        <Group draggable={!readonly}
               onDragMove={ceilPosition}
               onDragEnd={handleDrag}
               x={building.location.x}
               y={building.location.y}
        >
            <Text fontSize={14}
                  x={BUILDING_WIDTH / 2  - LABEL_WIDTH / 2}
                  y={-30}
                  text={building.label}
                  fill={color}
                  align={"center"}
                  width={LABEL_WIDTH}
            />
            <Circle
                x={BUILDING_WIDTH / 2}
                y={BUILDING_WIDTH / 2}
                strokeWidth={0.4 / scale}
                stroke={color}
                radius={60}
                visible={visible}
                onMouseLeave={eraseArea}
            />
            <Group onMouseEnter={enterBuilding}
                   onMouseLeave={leaveBuilding}
                   onClick={handleClickBuilding}
            >
                <Rect
                    width={BUILDING_WIDTH}
                    height={BUILDING_WIDTH}
                    fill={stroke? "gold" : color}
                    shadowColor={"red"}
                    shadowEnabled={!building.valid}
                    shadowBlur={20}
                />
                <Image image={buildingIcon}
                       width={BUILDING_ICON_SIZE}
                       height={BUILDING_ICON_SIZE}
                       fill={"green"}
                />
            </Group>
        </Group>
    )
}
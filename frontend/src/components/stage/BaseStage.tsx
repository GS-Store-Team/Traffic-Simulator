import React, {FC, PropsWithChildren, useCallback, useContext, useEffect, useState} from "react";
import {Stage} from "react-konva";
import {Size} from "../../Types";
import {AreaGrid} from "./AreaGrid";
import Konva from "konva";
import {StageContext} from "../../App";
import {MAX_SCALE, MIN_SCALE, SCALE_BY} from "../../Constants";

export const HEIGHT_SHIFT = 120

export const BaseStage : FC<PropsWithChildren> = ({ children}) => {
    const { scale, setScale, coordinates, setCoordinates } = useContext(StageContext)
    const [size, setSize] = useState<Size>({width:window.innerWidth, height:window.innerHeight - HEIGHT_SHIFT})

    // useEffect(() => {
    //     const handleResize = () => setSize({width: window.innerWidth, height:window.innerHeight - HEIGHT_SHIFT})
    //     window.addEventListener("resize", handleResize)
    //     return () => window.removeEventListener("resize", handleResize)
    // }, [])

    const handleWheel = useCallback((e: Konva.KonvaEventObject<WheelEvent>) => {
        e.evt.preventDefault();
        const stage = e.target.getStage();
        if(!stage){
            return;
        }
        const oldScale = stage.scaleX();
        const newScale = e.evt.deltaY < 0 ? oldScale * SCALE_BY : oldScale / SCALE_BY;
        if((scale < MIN_SCALE && oldScale > newScale) || (scale > MAX_SCALE && oldScale < newScale)) {
            return
        }
        const x = stage.getPointerPosition()?.x
        const y = stage.getPointerPosition()?.y
        if(!x || !y){
            return;
        }
        setCoordinates({
            x: (x / newScale - (x / oldScale - stage.x() / oldScale)) * newScale,
            y: (y / newScale - (y / oldScale - stage.y() / oldScale)) * newScale
        });
        setScale(newScale);
    }, [scale, setCoordinates, setScale])

    return (
        <Stage
            x={coordinates.x}
            y={coordinates.y}
            scaleX={scale}
            scaleY={scale}
            width={size.width}
            height={size.height}
            draggable={true}
            onWheel={handleWheel}
        >
            <AreaGrid size={size}/>
            {children}
        </Stage>
    )
}
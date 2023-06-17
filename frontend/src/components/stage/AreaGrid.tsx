import React, {FC, useCallback, useContext, useEffect, useMemo, useState} from "react";
import {Group, Layer, Line, Rect} from "react-konva";
import {Size} from "../../Types";
import {StageContext} from "../../App";
import {restClient} from "../../api/axios.config";
import {AreasPlacement} from "../../api/rest-client";
import {EditorContext} from "../../pages/Editor";

interface AreaGridProps{
    size: Size
}

export const AreaGrid : FC<AreaGridProps> = ({size}) => {
    const { scale } = useContext(StageContext)
    const { area } = useContext(EditorContext)


    const [placement, setPlacement] = useState<AreasPlacement>({elems:[]})

    useEffect(() => {
        restClient.getAreas().then(setPlacement)
    }, [])

    const areaColor = useCallback((key : number) => {
        if(!area){
            return
        }
        const currentArea = placement.elems.find(c => c.cellIds.includes(key))
        let tmp = -1
        if(area && currentArea?.areaVersionId === area) {
            tmp = currentArea.areaVersionId
        }
        switch (tmp){
            case 0: return "rgba(0,0,0,.1)"
            case 1: return "rgba(252,118,118,0.1)"
            case 2: return "rgba(255,221,128,0.29)"
            case 3: return "rgba(250,0,0,0.15)"
            case 4: return "rgba(53,169,59,0.1)"
            case 5: return "rgba(2,68,122,0.16)"
            case 6: return "rgba(239,101,255,0.1)"
            default: return "white"
        }
    }, [area, placement.elems])

    const grid = useMemo(() => {
        const arr = []
        const cellSize = 300
        const count = 20
        const offsetX = size.width /2 - cellSize * 20 / 2
        const offsetY = size.height/2 - cellSize * 20 / 2

        for(let i = 0; i<count; i++)
            for(let j = 0; j<count; j++){
                const key = i*count + j
                arr.push(<Rect key={key} x={j*cellSize + offsetX} y={i*cellSize + offsetY} fill={areaColor(key)} stroke={"black"} strokeWidth={1/10/scale} width={cellSize} height={cellSize}/>)
            }

        return arr
    }, [areaColor, scale, size.height, size.width])

    return(
        <Layer>
            <Group>
                {grid.map(r => r)}
                <Line x={0}
                      y={0}
                      points={[size.width/2 - 10 / scale, size.height/2, size.width/2 + 10 / scale, size.height/2]}
                      strokeWidth={2/scale}
                      stroke={"red"}
                />
                <Line x={0}
                      y={0}
                      points={[size.width/2, size.height/2 - 10 / scale, size.width/2, size.height/2 + 10 / scale]}
                      strokeWidth={2/scale}
                      stroke={"red"}
                />
            </Group>
        </Layer>
    )
}
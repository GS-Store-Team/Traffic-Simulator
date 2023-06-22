import React, {useCallback, useContext, useEffect, useMemo, useState} from "react";
import {Circle, Group, Layer, Line, Rect} from "react-konva";
import {StageContext} from "../../App";
import {restClient} from "../../api/axios.config";
import {AreasPlacement} from "../../api/rest-client";
import {EditorContext} from "../../pages/Editor";
import {RunnerContext} from "../../pages/Runner";
import {CELL_SIZE} from "../../Constants";

export const AreaGrid = () => {
    const { scale } = useContext(StageContext)
    const { area } = useContext(EditorContext)
    const { state } = useContext(RunnerContext)

    const [placement, setPlacement] = useState<AreasPlacement>({elems:[]})

    useEffect(() => {
        restClient.getAreas().then(setPlacement)
    }, [])

    const areaColor = useCallback((key : number) => {
        const currentArea = placement?.elems.find(c => c.cellIds.includes(key))
        let tmp = -1
        if(!area || currentArea?.areaVersionId === area.id) {
            tmp = currentArea ? currentArea.areaVersionId : -1
        }
        if(state && currentArea){
            tmp = -1
            if(state.areaVersionDTO.map(v => v.id).includes(currentArea.areaVersionId)){
                tmp = currentArea.areaVersionId
            }
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
    }, [area, placement, state])

    const grid = useMemo(() => {
        const arr = []
        const count = 20
        for(let i = 0; i<count; i++)
            for(let j = 0; j<count; j++){
                const key = i*count + j
                arr.push(<Rect key={key} x={j*CELL_SIZE } y={i*CELL_SIZE } fill={areaColor(key)} stroke={"black"} strokeWidth={1/10/scale} width={CELL_SIZE} height={CELL_SIZE}/>)
            }

        return arr
    }, [areaColor, scale])

    const shift = CELL_SIZE * 10

    return(
        <Layer>
            <Group>
                {grid.map(r => r)}
                <Circle x={0} y={0} fill={"red"} width={10}/>
                <Line points={[shift - 10 / scale, shift, shift + 10 / scale, shift]}
                      strokeWidth={2/scale}
                      stroke={"red"}
                />
                <Line points={[shift, shift - 10 / scale, shift, shift + 10 / scale]}
                      strokeWidth={2/scale}
                      stroke={"red"}
                />
            </Group>
        </Layer>
    )
}
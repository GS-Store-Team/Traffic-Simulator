import React, {useCallback, useContext, useEffect, useMemo, useState} from 'react';
import {Group, Line, Rect} from "react-konva";
import {Context} from "../router/AppRouter.jsx";
import Api from "../API.js";

export const Grid = ({width, height, selectedAreaId}) => {
    const {scale} = useContext(Context);
    const [myScale, setMyScale] = useState(1);
    const [cells, setCells] = useState(null)

    useEffect(() => {
        scale<0.8? setMyScale(0.8): setMyScale(scale);
    }, [scale])

    useEffect(() => {
        Api.getAreas().then(response => setCells(response.data.elems))
    }, [])

    const areaColor = useCallback((key) => {
        if(!cells) return
        const area = cells.find(c => c.cellIds.includes(key))
        if(!area) return
        let tmp
        if(area.areaVersionId !== selectedAreaId && selectedAreaId !== undefined) tmp = -1
        else tmp = area.areaVersionId
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
    }, [cells, selectedAreaId])

    const grid = useMemo(() => {
        const arr = []
        const size = 300
        const count = 20
        const offsetX = width/2 - size * 20 / 2
        const offsetY = height/2 - size * 20 / 2

        for(let i = 0; i<count; i++)
            for(let j = 0; j<count; j++){
                const key = i*count + j
                arr.push(<Rect key={key} x={j*size + offsetX} y={i*size + offsetY} fill={areaColor(key)} stroke={"black"} strokeWidth={1/10/myScale} width={size} height={size}/>)
            }

        return arr
    }, [areaColor, height, myScale, width])

    return (
        <Group>
            {grid.map(r => r)}
            <Line x={0}
                  y={0}
                  points={[width/2 - 10 / scale, height/2, width/2 + 10 / scale, height/2]}
                  strokeWidth={2/scale}
                  stroke={"red"}
                  />
            <Line x={0}
                  y={0}
                  points={[width/2, height/2 - 10 / scale, width/2, height/2 + 10 / scale]}
                  strokeWidth={2/scale}
                  stroke={"red"}
            />
        </Group>
    );
};
import {FC, useContext} from "react";
import {Circle, Group} from "react-konva";
import {RoadDTO} from "../../api/rest-client";
import {ceilPosition} from "../../utils";
import {StageContext} from "../../App";
import {MultiLanesRoad} from "./MultiLanesRoad";

interface RoadProps{
    road: RoadDTO
    readonly: boolean
}

export const Road : FC<RoadProps> = ({road, readonly}) => {
    const { scale } = useContext(StageContext);

    // const [visible, setVisible] = useState(false);
    // const [roadState, setRoadState] = useState({
    //     start:{x:road.x, y:road.y},
    //     end:{x:road.x1, y:road.y1},
    // })
    //
    // const [startX, setStartX] = useState(road.x);
    // const [startY, setStartY] = useState(road.y);
    // const [endX, setEndX] = useState(road.x1);
    // const [endY, setEndY] = useState(road.y);
    // const changerStart = (e) => {
    //     ceilPosition(e);
    //     setStartX(e.target.attrs.x);
    //     setStartY(e.target.attrs.y);
    // }
    //
    // const changerEnd = (e) => {
    //     ceilPosition(e);
    //     setEndX(e.target.attrs.x);
    //     setEndY(e.target.attrs.y);
    // }
    //
    // const changerGroup = (e) => {
    //     if(e.target.constructor.name === "Group")
    //         setRoadState({...roadState,
    //             start:{x:e.target.attrs.x+startX,
    //                 y:e.target.attrs.y+startY},
    //             end:{x:e.target.attrs.x+endX,
    //                 y:e.target.attrs.y+endY},
    //         });
    // }
    //
    // const enterRoad = () => {
    //     setVisible(true);
    // }
    //
    // const leaveRoad = () => {
    //     setVisible(false);
    // }
    //
    // const changeSettings = () =>{
    //     setRoadSettings({road:road, delete:remove, setRoad:setRS})
    // }


    return(
        <Group draggable={!readonly}
               onDragMove={ceilPosition}
               // onDragEnd={(e) => changerGroup(e)}
        >
            <MultiLanesRoad start={road.start} end={road.end} reverse={road.reverse} forward={road.forward} />
            <Circle
                x={road.start.x}
                y={road.start.y}
                fill={"green"}
                radius={ 5 / scale}
                draggable={!readonly}
                onDragMove={ceilPosition}
                // onDragEnd={(e) => setRoadState({...roadState,
                //     start:{x:e.target.attrs.x+e.target.parent.attrs.x,
                //         y:e.target.attrs.y+e.target.parent.attrs.y},
                // })}
            />
            <Circle
                x={road.end.x}
                y={road.end.y}
                fill={"gray"}
                radius={5/scale}
                draggable={!readonly}
                // onDragMove={(e) =>changerEnd(e)}
                // onDragEnd={(e) => setRoadState({...roadState,
                //     end:{x:e.target.attrs.x+e.target.parent.attrs.x,
                //         y:e.target.attrs.y+e.target.parent.attrs.y},
                // })}
            />
        </Group>
    )
}
import {FC} from "react";
import {Group} from "react-konva";
import {RoadDTO} from "../../api/rest-client";

interface RoadProps{
    road: RoadDTO
}

export const Road : FC<RoadProps> = ({road}) => {
    return(
        <Group>

        </Group>
    )
}
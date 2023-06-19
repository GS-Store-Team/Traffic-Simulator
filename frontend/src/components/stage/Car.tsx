import {FC} from "react";
import {Group} from "react-konva";
import {CarDTO} from "../../api/rest-client";

interface CarProps{
    car: CarDTO
}

export const Car : FC<CarProps> = ({car}) => {
    return(
        <Group>

        </Group>
    )
}
import {FC} from "react";
import {Circle} from "react-konva";
import {CarDTO} from "../../api/rest-client";

interface CarProps{
    car: CarDTO
}

export const Car : FC<CarProps> = ({car}) => {
    return(
        <Circle x={car.coordinates.x}
                y={car.coordinates.y}
                radius={6}
                fill={"red"}
        />
    )
}
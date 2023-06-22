import {FC} from "react";
import {Circle} from "react-konva";
import {CarDTO} from "../../api/rest-client";

interface CarProps{
    car: CarDTO
}

export const Car : FC<CarProps> = ({car}) => {
    console.log(car)
    return(
        <Circle x={car.coordinates.x}
                y={car.coordinates.y}
                radius={2}
                fill={"red"}
        />
    )
}
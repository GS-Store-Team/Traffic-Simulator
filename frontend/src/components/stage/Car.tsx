import {FC} from "react";
import {Circle, Group} from "react-konva";
import {CarDTO} from "../../api/rest-client";
import {BUILDING_WIDTH} from "../../Constants";

interface CarProps{
    car: CarDTO
}

export const Car : FC<CarProps> = ({car}) => {
    return(
        <Group>
            <Circle
                x={car.coordinates.x}
                y={car.coordinates.y}
                strokeWidth={0.4}
                radius={10}
                visible={true}
            />
        </Group>
    )
}
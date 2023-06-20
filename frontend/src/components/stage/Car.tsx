import {FC} from "react";
import {Circle, Group} from "react-konva";
import {CarDTO} from "../../api/rest-client";

interface CarProps{
    car: CarDTO
}

export const Car : FC<CarProps> = ({car}) => {
    console.log(car)
    if (car.coordinates){
        return(
            <Group>
                <Circle
                    x={car.coordinates.x}
                    y={car.coordinates.y}
                    strokeWidth={0.9}
                    stroke={"red"}
                    radius={10}
                    visible={true}
                />
            </Group>
        )
    }
    return null

}
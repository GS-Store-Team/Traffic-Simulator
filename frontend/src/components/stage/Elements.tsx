import { FC } from "react";
import {BuildingDTO, CarDTO, RoadDTO} from "../../api/rest-client";
import {Layer} from "react-konva";
import {Building} from "./Building";
import {Road} from "./Road";
import {Car} from "./Car";

interface ElementsProps{
    buildings: BuildingDTO[];
    roads: RoadDTO[];
    cars: CarDTO[];
    readonly?: boolean;
}

export const Elements : FC<ElementsProps> = ({buildings, roads, cars, readonly = false}) => {
    return (
        <Layer>
            {buildings.map(b => <Building key={b.id} building={b} readonly/>)}
            {roads.map(r => <Road key={r.id} road={r} readonly/>)}
            {cars.map(c => <Car key={c.id} car={c}/>)}
        </Layer>
    )
}
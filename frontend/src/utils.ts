import {KonvaEventObject} from "konva/lib/Node";
import {CELL_GRID} from "./Constants";

const mapCoordinateCeil = (cord: number) => {
    if (cord % CELL_GRID >= (CELL_GRID / 2)) {
        return (Math.floor(cord / CELL_GRID) + 1) * CELL_GRID;
    }
    return Math.floor(cord / CELL_GRID) * CELL_GRID;
}

export const ceilPosition = (e: KonvaEventObject<DragEvent>) => {
    e.target.attrs.x = mapCoordinateCeil(e.target.attrs.x);
    e.target.attrs.y = mapCoordinateCeil(e.target.attrs.y);
}
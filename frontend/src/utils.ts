import {KonvaEventObject} from "konva/lib/Node";
import {CELL_GRID} from "./Constants";
import {Coordinates} from "./api/rest-client";

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

export const calcPointsForAdditionalLanes = (start: Coordinates, end: Coordinates) => {
    const distance = Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2))

    let sin = Math.abs(end.y - start.y) / distance;
    let cos = Math.abs(end.x - start.x) / distance;

    if (end.y > start.y) sin *= -1;
    if (end.x < start.x) cos *= -1;

    const roadWidth = 5;

    return {
        forward: {
            first: {
                start: {x: start.x + roadWidth * sin, y: start.y + roadWidth * cos},
                end: {x: end.x + roadWidth * sin, y: end.y + roadWidth * cos}
            },
            second: {
                start: {x: start.x + 2 * roadWidth * sin, y: start.y + 2 * roadWidth * cos},
                end: {x: end.x + 2 * roadWidth * sin, y: end.y + 2 * roadWidth * cos}
            },
            third: {
                start: {x: start.x + 3 * roadWidth * sin, y: start.y + 3 * roadWidth * cos},
                end: {x: end.x + 3 * roadWidth * sin, y: end.y + 3 * roadWidth * cos}
            },
            fourth: {
                start: {x: start.x + 4 * roadWidth * sin, y: start.y + 4 * roadWidth * cos},
                end: {x: end.x + 4 * roadWidth * sin, y: end.y + 4 * roadWidth * cos}
            },
        },
        reverse: {
            first: {
                start: {x: end.x - roadWidth * sin, y: end.y - roadWidth * cos},
                end: {x: start.x - roadWidth * sin, y: start.y - roadWidth * cos}
            },
            second: {
                start: {x: end.x - 2 * roadWidth * sin, y: end.y - 2 * roadWidth * cos},
                end: {x: start.x - 2 * roadWidth * sin, y: start.y - 2 * roadWidth * cos}
            },
            third: {
                start: {x: end.x - 3 * roadWidth * sin, y: end.y - 3 * roadWidth * cos},
                end: {x: start.x - 3 * roadWidth * sin, y: start.y - 3 * roadWidth * cos}
            },
            fourth: {
                start: {x: end.x - 4 * roadWidth * sin, y: end.y - 4 * roadWidth * cos},
                end: {x: start.x - 4 * roadWidth * sin, y: start.y - 4 * roadWidth * cos}
            },
        },
    }
}
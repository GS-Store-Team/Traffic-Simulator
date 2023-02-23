export const defaultBuilding = {
    x: 0,
    y: 0,
    width: 50,
    height: 50,
    fill: "grey",
    shadowBlur: 2,
    draggable: true,
    enter: "gold"
}

export const defaultRoad = {
    x: 100,
    y: 50,
    x1:200,
    y1:50,
    pointRadius:5,
    pointFill: "grey",
    lineFill: "grey",
    lineStroke: 10,
    enter: "gold",
    shadowBlur: 0,
    forwardLanesCnt:1,
    reverseLanesCnt:1,
}

export const visualizationSettings = {
    showRoadCells:false,
}
export const mapCoordinateCeil = (cord)=> {
    const mapCell = localStorage.getItem("mapCell");
    if(cord % mapCell >= (mapCell/2))
        return (Math.floor(cord / mapCell) + 1)* mapCell;
    else return Math.floor(cord / mapCell)* mapCell;
}

export const ceilPosition = (e) =>{
    e.target.attrs.x = mapCoordinateCeil(e.target.attrs.x);
    e.target.attrs.y = mapCoordinateCeil(e.target.attrs.y);
}

export const calcPointsForAdditionalLanes = (coords) => {
    const distance = Math.sqrt(Math.pow(coords.start.x - coords.end.x, 2) + Math.pow(coords.start.y - coords.end.y, 2))

    let sin =  Math.abs(coords.end.y - coords.start.y) / distance;
    let cos =  Math.abs(coords.end.x - coords.start.x) / distance;

    if(coords.end.y>coords.start.y) sin*=-1;
    if(coords.end.x<coords.start.x) cos*=-1;

    const roadWidth = 5;

    return {forward:{first:{start:{x:coords.start.x + roadWidth*sin, y:coords.start.y + roadWidth*cos}, end:{x:coords.end.x + roadWidth*sin, y:coords.end.y + roadWidth*cos}},
                     second:{start:{x:coords.start.x + 2*roadWidth*sin, y:coords.start.y + 2*roadWidth*cos}, end:{x:coords.end.x + 2*roadWidth*sin, y:coords.end.y + 2*roadWidth*cos}},
                     third:{start:{x:coords.start.x + 3*roadWidth*sin, y:coords.start.y + 3*roadWidth*cos}, end:{x:coords.end.x + 3*roadWidth*sin, y:coords.end.y + 3*roadWidth*cos}},
                     fourth:{start:{x:coords.start.x + 4*roadWidth*sin, y:coords.start.y + 4*roadWidth*cos}, end:{x:coords.end.x + 4*roadWidth*sin, y:coords.end.y + 4*roadWidth*cos}},
                    },
            reverse:{first:{start:{x:coords.end.x - roadWidth*sin, y:coords.end.y - roadWidth*cos}, end:{x:coords.start.x - roadWidth*sin, y:coords.start.y - roadWidth*cos}},
                     second:{start:{x:coords.end.x - 2*roadWidth*sin, y:coords.end.y - 2*roadWidth*cos}, end:{x:coords.start.x - 2*roadWidth*sin, y:coords.start.y - 2*roadWidth*cos}},
                     third:{start:{x:coords.end.x - 3*roadWidth*sin, y:coords.end.y - 3*roadWidth*cos}, end:{x:coords.start.x - 3*roadWidth*sin, y:coords.start.y - 3*roadWidth*cos}},
                     fourth:{start:{x:coords.end.x - 4*roadWidth*sin, y:coords.end.y - 4*roadWidth*cos}, end:{x:coords.start.x - 4*roadWidth*sin, y:coords.start.y - 4*roadWidth*cos}},
                    },
    }
}
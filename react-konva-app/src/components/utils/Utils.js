
const mapCell = 10;
export const mapCoordinateCeil = (cord)=> {
    if(cord % mapCell >= (mapCell/2))
        return (Math.floor(cord / mapCell) + 1)* mapCell;
    else return Math.floor(cord / mapCell)* mapCell;
}

export const ceilPosition = (e) =>{
    e.target.attrs.x = mapCoordinateCeil(e.target.attrs.x);
    e.target.attrs.y = mapCoordinateCeil(e.target.attrs.y);
}
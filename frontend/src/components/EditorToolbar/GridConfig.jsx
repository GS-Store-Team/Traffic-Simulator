import React, {useEffect, useState} from 'react';
import classes from "./editortoolbar.module.css";

export const GridConfig = () => {

    const [gridSize, setGridSize] = useState({1:false, 2:true, 3:false, 4:false});

    useEffect(() => {
        if(gridSize["1"]) localStorage.setItem("mapCell", 5)
        if(gridSize["2"]) localStorage.setItem("mapCell", 10)
        if(gridSize["3"]) localStorage.setItem("mapCell", 25)
        if(gridSize["4"]) localStorage.setItem("mapCell", 50)
    }, [gridSize]);

    return (
        <form className={classes.my__grid}>
            <div className={classes.title}>Grid size (px)</div>
            <div className={classes.box}>
                <div className={classes.small__box}>
                    <label><input type={"radio"} name={"grid"} checked={gridSize["1"]} onChange={e => setGridSize({1:true, 2:false, 3:false, 4:false})}/>5</label>
                </div>
                <div className={classes.small__box}>
                    <label><input type={"radio"} name={"grid"} checked={gridSize["2"]} onChange={e => setGridSize({1:false, 2:true, 3:false, 4:false})}/>10</label>
                </div>
                <div className={classes.small__box}>
                    <label><input type={"radio"} name={"grid"} checked={gridSize["3"]} onChange={e => setGridSize({1:false, 2:false, 3:true, 4:false})}/>25</label>
                </div>
                <div className={classes.small__box}>
                    <label><input type={"radio"} name={"grid"} checked={gridSize["4"]} onChange={e => setGridSize({1:false, 2:false, 3:false, 4:true})}/>50</label>
                </div>

            </div>
        </form>
    );
};
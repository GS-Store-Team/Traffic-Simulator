import React from 'react';
import classes from "./menu.module.css";
import pointer from "../UI/images/pointer.png"
import plus from "../UI/images/plus.png"
import minus from "../UI/images/minus.png"

const Menu = (props) => {
    return (
        <div className={classes.my__menu}>
            <button
                onClick={(e) => props.addBuilding(e)}
                className={classes.my__button}>
                Add Building
            </button>

            <button
                onClick={(e) => props.addRoad(e)}
                className={classes.my__button}>
                Add Road
            </button>

            <img onClick={props.defaultPosition}
                 className={classes.my__pointer}
                 src={pointer}/>
            <img onClick={props.upscale}
                 className={classes.my__plus}
                 src={plus}/>
            <img onClick={props.downscale}
                 className={classes.my__minus}
                 src={minus}/>
        </div>
    );
};

export default Menu;
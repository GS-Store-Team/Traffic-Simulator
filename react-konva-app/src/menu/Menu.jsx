import React from 'react';
import classes from "./menu.module.css";

const Menu = (props) => {
    return (
        <div className={classes.my__menu}>
            <button
                onClick={props.addBuilding}
                className={classes.my__button}>
                Add Building
            </button>

            <button
                onClick={props.addRoad}
                className={classes.my__button}>
                Add Road
            </button>
        </div>
    );
};

export default Menu;
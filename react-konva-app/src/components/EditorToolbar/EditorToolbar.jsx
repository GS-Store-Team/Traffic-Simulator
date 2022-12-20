import React from 'react';
import classes from "./editortoolbar.module.css";

const EditorToolbar = (props) => {
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
        </div>
    );
};

export default EditorToolbar;
import React from 'react';
import classes from "./editortoolbar.module.css";
import {GridConfig} from "./GridConfig.jsx";
import {DefaultObjConfig} from "./DefaultObjConfig.jsx";

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

            <DefaultObjConfig conf={props.configureDefaultObj}/>

            <GridConfig />
        </div>
    );
};

export default EditorToolbar;
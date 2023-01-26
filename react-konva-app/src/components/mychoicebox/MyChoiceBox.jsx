import React from 'react';
import classes from "./mychoicebox.module.css";

export const MyChoiceBox = ({arr, current, setCurrent}) => {
    return (
            <form className={classes.box}>
                {arr.map(e => <input type={"radio"} checked={e === current} onChange={() => setCurrent(e)}/>)}
            </form>
    );
};
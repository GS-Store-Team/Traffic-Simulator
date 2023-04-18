import React from 'react';
import classes from "./header.module.css";
import {Link} from "react-router-dom";

export const Header = ({state}) => {
    return (
        <div className={classes.my__header}>
            <Link to={"/edit"}
                  className={state ? classes.my__button: classes.my__button__current}
                  style={{textDecoration: "none"}}>
                Construct map
            </Link>

            <Link to={"/run"}
                  className={!state ? classes.my__button: classes.my__button__current}
                  style={{textDecoration: "none"}}>
                Run simulation
            </Link>
        </div>
    );
};
import React, {useMemo} from 'react';
import classes from "./editortoolbar.module.css";
import {GridConfig} from "./GridConfig.jsx";
import {DefaultObjConfig} from "./DefaultObjConfig.jsx";
import Dropdown from 'react-bootstrap/Dropdown';
import {DropdownButton} from "react-bootstrap";

const EditorToolbar = ({fullMap, addBuilding, addRoad, configureDefaultObj, selectArea, selectedAreaId}) => {

    const dropDown = useMemo(() => {
        if(!fullMap) return

        const selected = fullMap.areas.find(a => a.id === selectedAreaId)
        const title = selected ? selected.label : "ALL"

        return(
            <DropdownButton title={title} >
                <Dropdown.Item onClick={()=> selectArea(undefined)}>ALL</Dropdown.Item>
                {fullMap.areas.map(a => <Dropdown.Item key={a.id} onClick={()=> selectArea(a.id)}>{a.label}</Dropdown.Item>)}
            </DropdownButton>
        )
    }, [fullMap, selectArea, selectedAreaId])

    return (
        <div className={classes.my__menu}>
            <button
                onClick={(e) => addBuilding(e)}
                className={classes.my__button}>
                Add Building
            </button>

            <button
                onClick={(e) => addRoad(e)}
                className={classes.my__button}>
                Add Road
            </button>

            {dropDown}

            <DefaultObjConfig conf={configureDefaultObj}/>

            <GridConfig />
        </div>
    );
};

export default EditorToolbar;
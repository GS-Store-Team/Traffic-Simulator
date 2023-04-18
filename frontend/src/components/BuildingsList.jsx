import React from 'react';
import {Group} from "react-konva";
import {Building} from "./Building.jsx";

const BuildingsList = (props) => {
    return (
        <Group>
            {props.buildings.map(b => <Building building={b} rm={props.rm} key={b.id}/>)}
        </Group>
    );
};

export default BuildingsList;
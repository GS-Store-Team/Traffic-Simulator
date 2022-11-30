import React from 'react';
import Building from "./Building";
import {Group} from "react-konva";

const BuildingsList = (props) => {
    return (
        <Group>
            {props.buildings.map(b => <Building building={b} rm={props.rm} key={b.id}/>)}
        </Group>
    );
};

export default BuildingsList;
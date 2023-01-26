import React from 'react';
import {Group} from "react-konva";
import {Road} from "./Road.jsx";

const RoadList = (props) => {
    return (
        <Group>
            {props.roads.map(r => <Road road={r} rm={props.rm} key={r.id}/>)}
        </Group>
    );
};

export default RoadList;
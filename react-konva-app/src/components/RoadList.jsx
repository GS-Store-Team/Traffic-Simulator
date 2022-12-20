import React from 'react';
import Road from "./Road";
import {Group} from "react-konva";

const RoadList = (props) => {

    return (
        <Group>
            {props.roads.map(r => <Road road={r} rm={props.rm} key={r.id}/>)}
        </Group>
    );
};

export default RoadList;
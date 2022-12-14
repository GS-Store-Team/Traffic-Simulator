import React, {useState} from 'react';
import {Layer, Stage} from "react-konva";
import Menu from "../menu/Menu.jsx";
import BuildingsList from "../components/BuildingsList.jsx";
import RoadList from "../components/RoadList.jsx";
import {Header} from "../components/header/Header.jsx";
import {Grid} from "../components/Grid";

export const Editor = () => {
    const defaultBuilding = {
        id:0,
        x: 0,
        y: 0,
        width: 50,
        height: 50,
        fill: "grey",
        shadowBlur: 0,
        draggable: true,
        enter: "gold"
    }
    const [buildings, setBuildings] = useState([]);
    const addBuilding = () =>{
        const building = {...defaultBuilding};
        building.id = Date.now();
        setBuildings([...buildings, building])
    }
    const removeBuilding = (building) =>{
        setBuildings(buildings.filter(b => b.id !== building.id))
    }

    const defaultRoad = {
        id:0,
        x: 100,
        y: 50,
        offSet: 100,
        pointRadius:6,
        pointFill: "grey",
        lineFill: "grey",
        lineStroke: 10,
        enter: "gold",
    }
    const [roads, setRoads] = useState([]);
    const addRoad = () =>{
        const road = {...defaultRoad};
        road.id = Date.now();
        setRoads([...roads, road])
    }
    const removeRoad = (road) =>{
        setRoads(roads.filter(r => r.id !== road.id))
    }

    return (
        <div>
            <Header state={false}/>
            <Menu addBuilding={addBuilding} addRoad={addRoad}/>
            <Stage
                width={1920}
                height={840}>
                <Layer>
                    <Grid />
                </Layer>
                <Layer>
                    <BuildingsList buildings={buildings} rm={removeBuilding} />
                    <RoadList roads={roads}  rm={removeRoad}/>
                </Layer>
            </Stage>
        </div>
    );
}
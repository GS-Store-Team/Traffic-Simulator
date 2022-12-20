import React, {useState} from 'react';
import {Layer} from "react-konva";
import EditorToolbar from "../components/EditorToolbar/EditorToolbar.jsx";
import BuildingsList from "../components/BuildingsList.jsx";
import RoadList from "../components/RoadList.jsx";
import {Header} from "../components/header/Header.jsx";
import {Grid} from "../components/Grid";
import {defaultBuilding, defaultRoad} from "../UI/DefaultObjects.js";
import {MyStage} from "../components/mystage/MyStage.jsx";

export const Editor = () => {
    const [buildings, setBuildings] = useState([]);
    const [roads, setRoads] = useState([]);
    const addBuilding = (e) =>{
        const building = {...defaultBuilding};
        building.id = Date.now();
        setBuildings([...buildings, building])
    }
    const removeBuilding = (building) =>{
        setBuildings(buildings.filter(b => b.id !== building.id))
    }
    const addRoad = (e) =>{
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
            <EditorToolbar addBuilding={addBuilding} addRoad={addRoad} />
            <MyStage layers={
                [
                    <Layer key={0}>
                        <Grid width={window.innerWidth} height={window.innerHeight-100}/>
                    </Layer>,
                    <Layer key={1}>
                        <BuildingsList buildings={buildings} rm={removeBuilding} />
                        <RoadList roads={roads}  rm={removeRoad}/>
                    </Layer>
                ]
            }/>
        </div>
    );
}
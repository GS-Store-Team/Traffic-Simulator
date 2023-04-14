import React, {useState} from 'react';
import {Layer} from "react-konva";
import EditorToolbar from "../components/EditorToolbar/EditorToolbar.jsx";
import BuildingsList from "../components/BuildingsList.jsx";
import RoadList from "../components/RoadList.jsx";
import {Header} from "../components/header/Header.jsx";
import {Grid} from "../components/Grid";
import {defaultBuilding, defaultRoad} from "../DefaultObjects.js";
import {MyStage} from "../components/mystage/MyStage.jsx";
import useMeasure from 'react-use-measure'
import {Info} from "../components/Info/Info.jsx";
import {Tune} from "../components/Tune/Tune.jsx";

export const Editor = () => {
    const [buildings, setBuildings] = useState([]);
    const [roads, setRoads] = useState([]);
    const [defRoad, setDefRoad] = useState({...defaultRoad})
    const [defBuilding, setDefBuilding] = useState({...defaultBuilding})
    const addBuilding = (e) =>{
        const building = {...defBuilding};
        building.id = Date.now();
        setBuildings([...buildings, building])
    }
    const removeBuilding = (building) =>{
        setBuildings(buildings.filter(b => b.id !== building.id))
    }
    const addRoad = (e) =>{
        const road = {...defRoad};
        road.id = Date.now();
        setRoads([...roads, road])
    }
    const removeRoad = (road) =>{
        setRoads(roads.filter(r => r.id !== road.id))
    }

    const [ref, bounds] = useMeasure()

    return (
        <div ref={ref}>
            <Header state={false}/>
            <EditorToolbar addBuilding={addBuilding} addRoad={addRoad} configureDefaultObj={{defRoad:defRoad,setDefRoad:setDefRoad, defBuilding:defBuilding, setDefBuilding:setDefBuilding}}/>
            <MyStage layers={
                [
                    <Layer key={1}>
                        <Grid width={bounds.width} height={bounds.height-100}/>
                    </Layer>,

                    <Layer key={2}>
                        <BuildingsList buildings={buildings} rm={removeBuilding} />
                        <RoadList roads={roads}  rm={removeRoad}/>
                    </Layer>
                ]
            }/>
            <Info />
            <Tune />
        </div>
    );
}
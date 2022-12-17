import React, {useContext, useEffect, useState} from 'react';
import {Layer, Stage} from "react-konva";
import Menu from "../menu/Menu.jsx";
import BuildingsList from "../components/BuildingsList.jsx";
import RoadList from "../components/RoadList.jsx";
import {Header} from "../components/header/Header.jsx";
import {Grid} from "../components/Grid";
import {defaultBuilding, defaultRoad, height, width} from "../UI/DefaultObjects.js";
import {Context} from "../router/AppRouter.jsx";

export const Editor = () => {
    const [buildings, setBuildings] = useState([]);
    const [roads, setRoads] = useState([]);
    const {setScale} = useContext(Context);
    const scaleBy = 1.10;
    const [stage, setStage] = useState({
        scale: 1,
        x: width/2,
        y: height/2
    });

    const defaultPosition = () => {
        setScale(1);
        setStage({scale: 1, x: width/2, y: height/2});
    }
    const upscale = () => {
        setScale(stage.scale*scaleBy*scaleBy);
        setStage({scale: stage.scale*scaleBy*scaleBy, x: stage.x, y: stage.y});
    }
    const downscale = () => {
        setScale(stage.scale/scaleBy/scaleBy);
        setStage({scale: stage.scale/scaleBy/scaleBy, x: stage.x, y: stage.y});
    }
    const addBuilding = () =>{
        const building = {...defaultBuilding};
        building.id = Date.now();
        setBuildings([...buildings, building])
    }
    const removeBuilding = (building) =>{
        setBuildings(buildings.filter(b => b.id !== building.id))
    }
    const addRoad = () =>{
        const road = {...defaultRoad};
        road.id = Date.now();
        setRoads([...roads, road])
    }
    const removeRoad = (road) =>{
        setRoads(roads.filter(r => r.id !== road.id))
    }

    const handleWheel = (e) => {
        e.evt.preventDefault();
        const stage = e.target.getStage();
        const oldScale = stage.scaleX();

        const mousePointTo = {
            x: stage.getPointerPosition().x / oldScale - stage.x() / oldScale,
            y: stage.getPointerPosition().y / oldScale - stage.y() / oldScale
        };

        const newScale = e.evt.deltaY < 0 ? oldScale * scaleBy : oldScale / scaleBy;

        setStage({
            scale: newScale,
            x: (stage.getPointerPosition().x / newScale - mousePointTo.x) * newScale,
            y: (stage.getPointerPosition().y / newScale - mousePointTo.y) * newScale
        });
        setScale(newScale);
    };

    return (
        <div>
            <Header state={false}/>
            <Menu addBuilding={addBuilding} addRoad={addRoad} defaultPosition={defaultPosition} upscale={upscale} downscale={downscale}/>
            <Stage
                x={stage.x}
                y={stage.y}
                offsetX={width/2}
                offsetY={height/2}
                width={width}
                height={height}
                scaleX={stage.scale}
                scaleY={stage.scale}
                onWheel={(e) => handleWheel(e)}
                draggable={true}>

                <Layer >
                    <Grid width={width} height={height} scale={stage.scale}/>
                </Layer>

                <Layer>
                    <BuildingsList buildings={buildings} rm={removeBuilding} />
                    <RoadList roads={roads}  rm={removeRoad}/>
                </Layer>
            </Stage>
        </div>
    );
}
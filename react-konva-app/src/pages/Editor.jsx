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

    function getWindowDimensions() {
        const { innerWidth: width, innerHeight: height } = window;
        return {
            width,
            height
        };
    }

    // eslint-disable-next-line no-restricted-globals
    const [width, setWidth] = useState(innerWidth);
    // eslint-disable-next-line no-restricted-globals
    const [height, setHeight] = useState(innerHeight-120);
    const [offset, setOffset] = useState({x:0,y:0})

    useEffect(() => {
        function handleWindowResize() {
            setWidth(getWindowDimensions().width);
            setHeight(getWindowDimensions().height-120);
        }

        window.addEventListener('resize', handleWindowResize);

        return () => {
            window.removeEventListener('resize', handleWindowResize);
        };
    }, []);

    useEffect(()=>{
        setStage({
            scale: 1,
            x: width/2,
            y: height/2
        });
    },[width, height]);


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
    const addBuilding = (e) =>{
        const building = {...defaultBuilding};
        building.id = Date.now();
        building.x = offset.x+50;
        building.y = offset.y+50;
        setBuildings([...buildings, building])
    }
    const removeBuilding = (building) =>{
        setBuildings(buildings.filter(b => b.id !== building.id))
    }
    const addRoad = (e) =>{
        const road = {...defaultRoad};
        road.id = Date.now();
        road.x = offset.x+50;
        road.y = offset.y+50;
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

    const handleDrag = (e) =>{
        if(e.target.constructor.name === "Stage")
            setOffset({x:(-e.target._lastPos.x + width/2),
                         y:(-e.target._lastPos.y + height/2)})
    }

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
                draggable={true}
                onDragEnd={(e) => handleDrag(e)}>

                <Layer >
                    <Grid width={width} height={height} scale={stage.scale}/>
                </Layer>

                <Layer>
                    <BuildingsList buildings={buildings} rm={removeBuilding} />
                    <RoadList roads={roads}  rm={removeRoad}/>
                </Layer>
            </Stage>;
        </div>
    );
}
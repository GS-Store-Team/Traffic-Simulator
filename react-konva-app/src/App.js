import React, {useState} from 'react';
import {Layer, Stage} from "react-konva";
import BuildingsList from "./components/BuildingsList";
import Menu from "./menu/Menu";
const App = () => {
    const defaultBuilding = {
        id:0,
        x: 0,
        y: 0,
        width: 50,
        height: 50,
        fill: "grey",
        shadowBlur: 2,
        draggable: true,
        stroke: "gold",
        widthStroke: 4,
    }

    const [buildings, setBuildings] = useState([]);

    const addBuilding = () =>{
        const building = {...defaultBuilding};
        building.id = Date.now();
        setBuildings([...buildings, building])
    }

    const removeBuilding = (building) =>{
        setBuildings(buildings.filter(b => b !== building))
    }

  return (
      <div>
          <Menu addBuilding={addBuilding}/>
          <Stage
              width={2100}
              height={1000}>
              <Layer>
                  <BuildingsList buildings={buildings} add={addBuilding} rm={removeBuilding} />
              </Layer>
          </Stage>
      </div>
  );
}
export default App;
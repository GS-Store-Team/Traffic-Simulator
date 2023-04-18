import {BrowserRouter, Route, Routes} from "react-router-dom";
import {publicRoutes} from "./Routes";
import {createContext, useState} from "react";

export const Context = createContext(null);
export const AppRouter = () => {
    const [scale, setScale] = useState(1);
    const [shift, setShift] = useState({x:0,y:0});
    const [road, setRoad] = useState(null);
    const [building, setBuilding] = useState(null);
    const [roadSettings, setRoadSettings] = useState(null);
    const [buildingSettings, setBuildingSettings] = useState(null)

    return (
        <Context.Provider value={{scale,setScale, shift, setShift, road, setRoad, building, setBuilding, roadSettings, setRoadSettings, buildingSettings, setBuildingSettings}}>
            <BrowserRouter>
                <Routes>
                    {publicRoutes.map((r,index) =>
                        <Route
                            path={r.path}
                            element={r.component}
                            key={index}
                        />)}
                </Routes>
            </BrowserRouter>
        </Context.Provider>
    );
};
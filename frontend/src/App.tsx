import React, {createContext, useCallback, useEffect, useMemo, useState} from 'react';
import {createBrowserRouter, Navigate, RouterProvider} from "react-router-dom";
import {Editor} from "./pages/Editor";
import {Runner} from "./pages/Runner";
import {InfoModal} from "./components/default/modal/InfoModal";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import '../node_modules/bootstrap/dist/js/bootstrap.js';
import {Coordinates} from "./api/rest-client";
import {INITIAL_SCALE} from "./Constants";
import {DefaultObjectProvider} from "./components/contexts/DefaultObjectProvider";
import {BootstrapContextProvider} from "./components/contexts/BootstrapContextProvider";

const router = createBrowserRouter([
    {path: "/editor", element: <Editor/>},
    {path: "/runner", element: <Runner/>},
    {path: "/*", element: <Navigate to="/editor" replace={true}/>},
]);

type StageContextType = {
    scale: number
    setScale(scale: number):void;
    coordinates: Coordinates
    setCoordinates(stage: Coordinates):void
}

export const StageContext = createContext<StageContextType>({
    scale:1,
    setScale() {},
    coordinates:{x:0, y:0},
    setCoordinates() {}
})

function App() {
    const [scale, setScale] = useState<number>(INITIAL_SCALE)
    const [coordinates, setCoordinates] = useState<{x: number, y: number}>({x:0, y:0})

    const [infoModal, setInfoModal] = useState<string>('')

    const handleCloseModal = useCallback(() => setInfoModal(''), [])

    useEffect(() => {
        const listener = ()=>setInfoModal("Server is unreachable.")
        window.addEventListener("ERR_NETWORK", listener)
        return () => {
            window.removeEventListener("ERR_NETWORK", listener)
        }
    }, [])

    const context: StageContextType = useMemo(() => {
        return { scale, setScale, coordinates, setCoordinates}
    }, [coordinates, scale])

    return (
        <BootstrapContextProvider>
            <DefaultObjectProvider>
                <StageContext.Provider value={context}>
                    <RouterProvider router={router}/>
                    {infoModal && <InfoModal $height={"300px"} title={"NETWORK ERROR"} message={infoModal} onOk={handleCloseModal}/>}
                </StageContext.Provider>
            </DefaultObjectProvider>
        </BootstrapContextProvider>
    );
}

export default App;

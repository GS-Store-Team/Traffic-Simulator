import React, {useCallback, useEffect, useState} from 'react';
import {createBrowserRouter, Navigate, RouterProvider} from "react-router-dom";
import {Editor} from "./pages/Editor";
import {Runner} from "./pages/Runner";
import {InfoModal} from "./components/default/modal/InfoModal";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

const router = createBrowserRouter([
    {path: "/editor", element: <Editor/>},
    {path: "/runner", element: <Runner/>},
    {path: "/*", element: <Navigate to="/editor" replace={true}/>},
]);

function App() {
    const [infoModal, setInfoModal] = useState<string>('')

    const handleCloseModal = useCallback(() => setInfoModal(''), [])

    useEffect(() => {
        const listener = ()=>setInfoModal("Server is unreachable.")
        window.addEventListener("NETWORK_ERROR", listener)
        return () => {
            window.removeEventListener("ERR_BAD_REQUEST", listener)
        }
    }, [])

    return (
        <>
            <RouterProvider router={router}/>
            {infoModal && <InfoModal $height={"300px"} title={"NETWORK ERROR"} message={infoModal} onOk={handleCloseModal}/>}
        </>
    );
}

export default App;

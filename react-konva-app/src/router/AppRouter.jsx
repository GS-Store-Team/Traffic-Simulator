import {BrowserRouter, Route, Routes} from "react-router-dom";
import {publicRoutes} from "./Routes";
import {createContext, useState} from "react";

export const Context = createContext(null);
export const AppRouter = () => {
    const [scale, setScale] = useState(1);

    return (
        <Context.Provider value={{scale,setScale}}>
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
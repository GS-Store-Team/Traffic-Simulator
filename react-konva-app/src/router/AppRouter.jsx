import {BrowserRouter, Route, Routes} from "react-router-dom";
import {publicRoutes} from "./Routes";

export const AppRouter = () => {

    return (
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
    );
};
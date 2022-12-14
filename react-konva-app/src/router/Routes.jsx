import React from 'react';
import {Editor} from "../pages/Editor";
import {Runner} from "../pages/Runner.jsx";
import {useNavigate} from "react-router-dom";

const NoMatch = ({path}) => {
    const navigate = useNavigate();
    React.useEffect(() => {
        navigate(path);
    }, []);
    return (<div />);
};

export const publicRoutes = [
    {path:"/edit", component: <Editor />},
    {path:"/run", component: <Runner />},
    {path:"*", component: <NoMatch path={"/run"} />}
]


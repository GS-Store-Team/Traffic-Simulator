import React from 'react';
import ReactDOM from 'react-dom/client';
import {AppRouter} from "./router/AppRouter.jsx";
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
        <AppRouter />
);

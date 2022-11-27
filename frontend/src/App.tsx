import React from 'react';
import {Navigate, Route, Routes} from 'react-router-dom';
import {AuthPage} from "./pages/AuthPage";
import {HomePage} from "./pages/HomePage";

function App() {
    return (
        <Routes>
            <Route path="/" element={ <Navigate to={`${process.env.REACT_APP_AUTH_PAGE}`} /> } />
            <Route path={process.env.REACT_APP_AUTH_PAGE} element={ <AuthPage/> } />
            <Route path={process.env.REACT_APP_HOME_PAGE} element={ <HomePage/> } />
        </Routes>
    );
}

export default App;

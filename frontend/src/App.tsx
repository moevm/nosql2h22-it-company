import React from 'react';
import {Navigate, Route, Routes} from 'react-router-dom';
import {AuthPage} from "./pages/AuthPage";
import {HomePage} from "./pages/HomePage";

function App() {
    return (
        <Routes>
            <Route path="/" element={ <Navigate to="/auth" /> } />
            <Route path="/auth" element={ <AuthPage/> } />
            <Route path="/home" element={ <HomePage/> } />
        </Routes>
    );
}

export default App;

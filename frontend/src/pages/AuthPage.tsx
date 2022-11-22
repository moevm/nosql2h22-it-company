import React from "react";
import {Auth} from "../components/Auth";
import './AuthPage.css';

export function AuthPage() {
    return (
        <>
            <div className="title">
                <h1>ИС для IT-компаний</h1>
                <img src='auth_image.png' alt="Logo" />
            </div>
            <Auth />
        </>
    );
}
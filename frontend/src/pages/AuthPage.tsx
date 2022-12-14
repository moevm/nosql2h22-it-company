import React, {useEffect} from "react";
import {Auth} from "../components/Auth";
import {AUTH_PAGE_TITLE, AUTH_PAGE_IMAGE} from "../constants";
import './AuthPage.css';

export function AuthPage() {
    useEffect(() => {
        document.title = AUTH_PAGE_TITLE;
    }, []);

    return (
        <div className="auth-background">
            <div className="auth-div">
                <h1>ИС для IT-компаний</h1>
                <img src={AUTH_PAGE_IMAGE} alt="Logo" />
            </div>
            <Auth />
        </div>
    );
}
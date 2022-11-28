import React, {useEffect, useState} from "react";
import {ActiveModule, Header} from "../components/Header";
import {AUTH_HOME_TITLE} from "../constants";
import {NoModule} from "../components/NoModule";

export function HomePage() {
    const [activeModule, setActiveModule] = useState<ActiveModule>(ActiveModule.NONE);

    useEffect(() => {
        document.title = AUTH_HOME_TITLE;
    }, []);

    return (
        <div style={{minHeight: '100vh', width: '100%'}}>
            <Header activeModule={activeModule} setActiveModule={setActiveModule} />
            {activeModule === ActiveModule.NONE && <NoModule />}
        </div>
    );
}
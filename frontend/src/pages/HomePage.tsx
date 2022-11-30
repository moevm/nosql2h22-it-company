import React, {useEffect, useState} from "react";
import {ActiveModule, Header} from "../components/Header";
import {NoModule} from "../components/NoModule";
import {WatcherModule} from "../components/WatcherModule";
import {PersonModule} from "../components/PersonModule";
import {DocumentModule} from "../components/DocumentModule";
import {HOME_PAGE_TITLE} from "../constants";

export function HomePage() {
    const [activeModule, setActiveModule] = useState<ActiveModule>(ActiveModule.NONE);

    useEffect(() => {
        document.title = HOME_PAGE_TITLE;
    }, []);

    return (
        <div style={{minHeight: '100vh', width: '100%'}}>
            <Header activeModule={activeModule} setActiveModule={setActiveModule} />
            {activeModule === ActiveModule.NONE && <NoModule />}
            {activeModule === ActiveModule.WATCHER && <WatcherModule />}
            {activeModule === ActiveModule.PERSON && <PersonModule />}
            {activeModule === ActiveModule.DOCUMENT && <DocumentModule />}
        </div>
    );
}
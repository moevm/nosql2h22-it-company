import React, {useEffect, useState} from "react";
import {PersonOutline, GroupOutlined} from "@mui/icons-material";
import {DocumentUserInfo} from "./DocumentUserInfo";
import {DocumentAllInfo} from "./DocumentAllInfo";
import {Footer} from "./Footer";
import {ImportExport} from "./ImportExport";
import {DOCUMENT_PAGE_TITLE} from "../constants";

export function DocumentModule() {
    const [showAllDocuments, setShowAllDocuments] = useState<boolean>(false);
    const [action, setAction] = useState<string | null>(null);

    useEffect(() => {
        document.title = DOCUMENT_PAGE_TITLE;
    }, []);

    return (
        <>
            {showAllDocuments ? <DocumentAllInfo /> : <DocumentUserInfo />}
            <ImportExport action={action} setAction={setAction} dbs={[{id: "documents", name: "Справки"}]} />
            <Footer colorable handle={(data) => {setShowAllDocuments(data === 1)}} setAction={setAction}>
                <PersonOutline fontSize='large' />
                <GroupOutlined fontSize='large' />
            </Footer>
        </>
    );
}

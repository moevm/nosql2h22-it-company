import React, {useEffect, useState} from "react";
import {PersonOutline, GroupOutlined} from "@mui/icons-material";
import {DocumentUserInfo} from "./DocumentUserInfo";
import {DocumentAllInfo} from "./DocumentAllInfo";
import {Footer} from "./Footer";
import {DOCUMENT_PAGE_TITLE} from "../constants";

export function DocumentModule() {
    const [showAllDocuments, setShowAllDocuments] = useState<boolean>(false);

    useEffect(() => {
        document.title = DOCUMENT_PAGE_TITLE;
    }, []);

    return (
        <>
            {showAllDocuments ? <DocumentAllInfo /> : <DocumentUserInfo />}
            <Footer handle={(data) => {setShowAllDocuments(data === 1)}}>
                <PersonOutline />
                <GroupOutlined />
            </Footer>
        </>
    );
}
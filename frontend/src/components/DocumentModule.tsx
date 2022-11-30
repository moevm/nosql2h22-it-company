import React, {useEffect, useState} from "react";
import jwt_decode from "jwt-decode";
import {PersonOutline, GroupOutlined} from "@mui/icons-material";
import {DocumentUserInfo} from "./DocumentUserInfo";
import {DocumentAllInfo} from "./DocumentAllInfo";
import {Footer} from "./Footer";
import {DOCUMENT_PAGE_TITLE} from "../constants";
import {IToken} from "../models";

export function DocumentModule() {
    const [showAllDocuments, setShowAllDocuments] = useState<boolean>(false);
    const [extendedRights, setExtendedRights] = useState<boolean>(false);

    useEffect(() => {
        document.title = DOCUMENT_PAGE_TITLE;
        let token: IToken = jwt_decode(localStorage.accessToken);
        setExtendedRights(token.role === "HR");
    }, []);

    return (
        <>
            {showAllDocuments ? <DocumentAllInfo /> : <DocumentUserInfo />}
            {extendedRights && <Footer handle={(data) => {setShowAllDocuments(data === 0)}}>
                <PersonOutline />
                <GroupOutlined />
            </Footer>}
        </>
    );
}
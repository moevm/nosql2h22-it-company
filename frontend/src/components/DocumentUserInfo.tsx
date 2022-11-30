import React from "react";
import {DocumentDeliver} from "./DocumentDeliver";
import {DocumentUserList} from "./DocumentUserList";

export function DocumentUserInfo() {
    return (
        <>
            <DocumentDeliver />
            <DocumentUserList />
        </>
    );
}
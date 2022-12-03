import React from "react";
import Typography from "@mui/material/Typography";
import {DocumentDeliver} from "./DocumentDeliver";
import {DocumentUserList} from "./DocumentUserList";

export function DocumentUserInfo() {
    return (
        <>
            <DocumentDeliver />
            <Typography variant="h6" component="span">
                Мои справки:
            </Typography>
            <DocumentUserList />
        </>
    );
}
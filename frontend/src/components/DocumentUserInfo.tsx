import React, {useState} from "react";
import Typography from "@mui/material/Typography";
import {DocumentDeliver} from "./DocumentDeliver";
import {DocumentUserList} from "./DocumentUserList";

export function DocumentUserInfo() {
    const [deliveredDocument, setDeliveredDocument] = useState<IDocument | null>(null);

    const handleDeliver = (document: IDocument) => {
        setDeliveredDocument(document);
    }

    return (
        <>
            <DocumentDeliver onDeliver={handleDeliver} />
            <Typography variant="h6" component="span">
                Мои справки:
            </Typography>
            <DocumentUserList addedDocument={deliveredDocument} />
        </>
    );
}
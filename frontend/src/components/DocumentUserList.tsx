import React, {useEffect, useState} from "react";
import Paper from "@mui/material/Paper";
import Stack from "@mui/material/Stack";
import Typography from "@mui/material/Typography";
import {styled} from "@mui/material/styles";
import {documentRequest} from "../requests/httpRequests";
import {DOCUMENT_TYPES} from "../constants";

interface IProps {
    addedDocument: IDocument | null
}

const Item = styled(Paper)(({theme}) => ({
    backgroundColor: "#EFEFEF",
    ...theme.typography.body2,
    padding: theme.spacing(3),
    borderRadius: "20px"
}));

const statusName = [
    {status: "ORDERED", name: "Заказано"},
    {status: "IN_PROGRESS", name: "В процессе"},
    {status: "DONE", name: "Готово"},
    {status: "CANCELED", name: "Отменено"}
];

export function DocumentUserList({addedDocument}: IProps) {
    const [documentList, setDocumentList] = useState<IDocument[]>([]);

    useEffect(() => {
        documentRequest.get<IDocument[]>(
            `${process.env.REACT_APP_DOCUMENT_GET_ALL_OWN}`
        ).then(response => {
            setDocumentList(response.data);
        });
    }, []);

    useEffect(() => {
        if (addedDocument) {
            setDocumentList([
                ...documentList,
                addedDocument
            ]);
        }
    }, [addedDocument]);

    return (
        <Stack spacing={2}>
            {documentList.map((data: IDocument) => (
                <Item elevation={6}>
                    <Typography variant="body1" component="span">
                        {DOCUMENT_TYPES.find(document => document.name === data.type)?.value}
                    </Typography>
                    <br />
                    <Typography variant="body1" component="span">
                        Дата заказа: {data.orderDate}
                    </Typography>
                    <br />
                    <Typography variant="body1" component="span">
                        Дата выдачи: {data.completeDate ? data.completeDate.substr(0, 10) : "не выдано"}
                    </Typography>
                    <br />
                    <Typography variant="body1" component="span">
                        Статус: {statusName.find(status => status.status === data.status)?.name}
                    </Typography>
                </Item>
            ))}
        </Stack>
    );
}
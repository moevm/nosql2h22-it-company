import React, {useEffect, useState} from "react";
import axios from "axios";
import Paper from "@mui/material/Paper";
import Stack from "@mui/material/Stack";
import Typography from "@mui/material/Typography";
import {styled} from "@mui/material/styles";
import {IDocument} from "../models";
import {DOCUMENT_TYPES} from "../constants";

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

export function DocumentUserList() {
    const [documentList, setDocumentList] = useState<IDocument[]>([]);

    useEffect(() => {
        axios.get<IDocument[]>(`${process.env.REACT_APP_DOCUMENT_HOST}/document/all-own`, {
            headers: {
                Authorization: `Bearer ${localStorage.accessToken}`
            }
        }).then(response => {
            setDocumentList(response.data);
        });
    }, []);

    return (
        <Stack spacing={2}>
            {documentList.map((data: IDocument) => (
                <Item elevation={6}>
                    <Typography variant="body1" component="span">
                        {DOCUMENT_TYPES.find(document => document.name === data.type)?.value}
                    </Typography>
                    <br/>
                    <Typography variant="body1" component="span">
                        Дата заказа: {data.orderDate}
                    </Typography>
                    <br/>
                    <Typography variant="body1" component="span">
                        Дата выдачи: {data.completeDate ?? "неизвестно"}
                    </Typography>
                    <br/>
                    <Typography variant="body1" component="span">
                        Статус: {statusName.find(status => status.status === data.status)?.name}
                    </Typography>
                </Item>
            ))}
        </Stack>
    );
}

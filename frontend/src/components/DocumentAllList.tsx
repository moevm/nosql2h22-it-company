import React, {useEffect, useState} from "react";
import Paper from "@mui/material/Paper";
import Stack from "@mui/material/Stack";
import Typography from "@mui/material/Typography";
import {styled} from "@mui/material/styles";
import {documentRequest} from "../utils/HTTPRequest";
import {DOCUMENT_TYPES} from "../constants";

interface IProps {
    sortRequest: string,
    typeRequest: string,
    statusRequest: string
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

type PersonDocument = {
    person: {
        name: string,
        surname: string,
        contacts: {
            phoneNumber: string,
            email: string
        }
    },
    document: IDocument
};

export function DocumentAllList({sortRequest, typeRequest, statusRequest}: IProps) {
    const [documentList, setDocumentList] = useState<PersonDocument[]>([]);

    const getDocuments = () => {
        documentRequest.get<PersonDocument[]>(`${process.env.REACT_APP_DOCUMENT_GET_ALL}`, {
            params: {
                sort: sortRequest,
                type: typeRequest,
                status: statusRequest
            }
        }).then(response => {
            setDocumentList(response.data);
        });
    }

    useEffect(() => {
        getDocuments();
    }, []);

    useEffect(() => {
        getDocuments();
    }, [sortRequest, typeRequest, statusRequest]);

    return (
        <Stack spacing={2}>
            {documentList.map((data: PersonDocument) => (
                <Item elevation={6}>
                    <Typography variant="body1" component="span">
                        {DOCUMENT_TYPES.find(document => document.name === data.document.type)?.value}
                    </Typography>
                    <br />
                    <Typography variant="body1" component="span">
                        Дата заказа: {data.document.orderDate}
                    </Typography>
                    <br />
                    <Typography variant="body1" component="span">
                        Дата выдачи: {data.document.completeDate ? data.document.completeDate.substr(0, 10) : "не выдано"}
                    </Typography>
                    <br />
                    <Typography variant="body1" component="span">
                        Статус: {statusName.find(status => status.status === data.document.status)?.name}
                    </Typography>
                    <br />
                    <hr />
                    <br />
                    <Typography variant="body1" component="span">
                        ФИ: {data.person.surname} {data.person.name}
                    </Typography>
                    <br />
                    <Typography variant="body1" component="span">
                        ({data.person.contacts.phoneNumber}, {data.person.contacts.email})
                    </Typography>
                </Item>
            ))}
        </Stack>
    );
}

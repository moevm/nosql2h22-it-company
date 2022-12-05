import React, {useEffect, useState} from "react";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import FormControl from "@mui/material/FormControl";
import Stack from "@mui/material/Stack";
import TextField from "@mui/material/TextField";
import {Add, Remove} from "@mui/icons-material";
import {Footer} from "./Footer";
import {ImportExport} from "./ImportExport";
import {PersonAdvancedSearch} from "./PersonAdvancedSearch";
import {PersonInfo} from "./PersonInfo";
import {PersonSearch} from "./PersonSearch";
import {authAdminRequest, personAdminRequest, personRequest} from "../utils/HTTPRequest";
import {useTypedSelector} from "../hooks/useTypedSelector";
import {PERSON_PAGE_TITLE, ROLES} from "../constants";

interface IID {
    id: string
}

interface IAddingPerson {
    [key: string]: number | string | string[];
    login: string,
    password: string,
    id: string,
    name: string,
    surname: string,
    patronymic: string,
    sex: string,
    birthday: string,
    firstWorkDate: string,
    position: string,
    status: string,
    phoneNumber: string,
    email: string,
    jobTimeStart: string,
    jobTimeEnd: string,
    officeId: string,
    passportNumber: string,
    passportIssuedPlace: string,
    passportIssuedDate: string,
    nationality: string,
    address: string,
    salary: number,
    projectIds: string[],
    comment: string
}

const initialState: IAddingPerson = {
    login: "",
    password: "",
    id: "",
    name: "",
    surname: "",
    patronymic: "",
    sex: "",
    birthday: "",
    firstWorkDate: "",
    position: "",
    status: "",
    phoneNumber: "",
    email: "",
    jobTimeStart: "",
    jobTimeEnd: "",
    officeId: "6359c11c2416d454722f826f",
    passportNumber: "",
    passportIssuedPlace: "",
    passportIssuedDate: "",
    nationality: "",
    address: "",
    salary: 0,
    projectIds: [],
    comment: ""
}

const addParams = [
    {id: "login", name: "Логин", type: "text", required: true},
    {id: "password", name: "Пароль", type: "text", required: true},
    {id: "name", name: "Имя", type: "text", required: true},
    {id: "surname", name: "Фамилия", type: "text", required: true},
    {id: "patronymic", name: "Отчество", type: "text", required: false},
    {id: "sex", name: "Пол", type: "text", required: true},
    {id: "birthday", name: "Дата рождения", type: "date", required: true},
    {id: "position", name: "Должность", type: "text", required: true},
    {id: "status", name: "Статус", type: "text", required: true},
    {id: "phoneNumber", name: "Телефон", type: "text", required: true},
    {id: "email", name: "Email", type: "email", required: true},
    {id: "jobTimeStart", name: "Начало рабочего дня", type: "text", required: true},
    {id: "jobTimeEnd", name: "Конец рабочего дня", type: "text", required: true},
    {id: "passportNumber", name: "Номер паспорта", type: "text", required: true},
    {id: "passportIssuedPlace", name: "Место выдачи паспорта", type: "text", required: true},
    {id: "passportIssuedDate", name: "Дата выдачи паспорта", type: "date", required: true},
    {id: "nationality", name: "Национальность", type: "text", required: true},
    {id: "address", name: "Адрес", type: "text", required: true},
    {id: "salary", name: "Зарплата", type: "text", required: true},
    {id: "comment", name: "Комментарий", type: "text", required: false}
];

const getCurrentDay = () => {
    const date = new Date();
    const year = `${date.getFullYear()}`;
    const month = date.getMonth() < 9 ? `0${date.getMonth() + 1}` : `${date.getMonth() + 1}`;
    const day = date.getDate() < 10 ? `0${date.getDate()}` : `${date.getDate()}`;
    return `${year}-${month}-${day}`;
}

const databases = [
    {id: "people", name: "Пользователи"},
    {id: "offices", name: "Офисы"},
    {id: "projects", name: "Проекты"}
];

export function PersonModule() {
    const [personInfo, setPersonInfo] = useState<IPerson | null>(null);
    const [newPersonInfo, setNewPersonInfo] = useState<IAddingPerson>(initialState);
    const [advancedSearch, setAdvancedSearch] = useState<boolean>(false);
    const [changePersonAbility, setChangePersonAbility] = useState<boolean>(false);
    const [processDialog, setProcessDialog] = useState<string>("add");
    const [openDialog, setOpenDialog] = useState<boolean>(false);
    const [action, setAction] = useState<string | null>(null);

    const userState: IUserState = useTypedSelector(state => state.user);

    useEffect(() => {
        document.title = PERSON_PAGE_TITLE;
        personRequest.get<IPerson>(
            `${process.env.REACT_APP_PERSON_GET}`
        ).then(response => {
            setPersonInfo(response.data);
        }).catch((error: IError) => {
            console.log(error);
        });
    }, []);


    useEffect(() => {
        setChangePersonAbility(userState?.advancedRole);
    }, [personInfo]);

    const handleAddClick = () => {
        setProcessDialog("add");
        setOpenDialog(true);
    }

    const handleRemoveClick = () => {
        setProcessDialog("remove");
        setOpenDialog(true);
    }

    const handleAddPerson = () => {
        authAdminRequest.post<IID>(`${process.env.REACT_APP_AUTH_SIGN_UP}`, {
            login: newPersonInfo.login,
            password: newPersonInfo.password,
            role: ROLES[newPersonInfo.position as keyof typeof ROLES]
        }).then(authResponse => {
            console.log(authResponse.data.id);
            personAdminRequest.post<IMessage>(`${process.env.REACT_APP_PERSON_SIGN_UP}`, {
                id: authResponse.data.id,
                name: newPersonInfo.name,
                surname: newPersonInfo.surname,
                patronymic: newPersonInfo.patronymic,
                sex: newPersonInfo.sex,
                birthday: newPersonInfo.birthday,
                firstWorkDate: getCurrentDay(),
                position: newPersonInfo.position,
                status: newPersonInfo.status,
                contacts: {
                    phoneNumber: newPersonInfo.phoneNumber,
                    email: newPersonInfo.email
                },
                jobTime: {
                    start: newPersonInfo.jobTimeStart,
                    end: newPersonInfo.jobTimeEnd
                },
                officeId: "6359c11c2416d454722f826f",
                confidential: {
                    passportData: {
                        number: newPersonInfo.passportNumber,
                        issuedPlace: newPersonInfo.passportIssuedPlace,
                        issuedDate: newPersonInfo.passportIssuedDate
                    },
                    nationality: newPersonInfo.nationality,
                    address: newPersonInfo.address,
                    salary: newPersonInfo.salary,
                    projectIds: [
                        "638c8f6c4076bb8ce10a1d52",
                        "638c8fdf5398a82b45a664fa",
                        "638c911022fe2dfca0b16ace"
                    ]
                },
                comment: newPersonInfo.comment
            }).catch((error: IError) => {
                // TODO: add feedback
                console.log(error);
            });
        }).catch((error: IError) => {
            // TODO: add feedback
            console.log(error);
        });
        setOpenDialog(false);
    }

    const handleRemovePerson = () => {
        personAdminRequest.delete(`${process.env.REACT_APP_PERSON_GET}/${personInfo?.id}`);
        setOpenDialog(false);
    }

    const handleCloseDialog = () => {
        setOpenDialog(false);
    }

    const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
        setNewPersonInfo({
            ...newPersonInfo,
            [event.target.id]: event.target.value
        });
    }

    return (
        <>
            {advancedSearch ? <PersonAdvancedSearch setPerson={setPersonInfo} setAdvancedSearch={setAdvancedSearch} /> : <Stack>
                <PersonSearch setPerson={setPersonInfo} setAdvancedSearch={setAdvancedSearch} />
                <PersonInfo person={personInfo} />
                {changePersonAbility && <Footer handle={(data) => {data === 0 ? handleAddClick() : handleRemoveClick()}} setAction={setAction}>
                    <Add fontSize='large' />
                    {userState?.id !== personInfo?.id ? <Remove fontSize='large' /> : <></>}
                </Footer>}
            </Stack>}
            <Dialog open={openDialog} onClose={handleCloseDialog}>
                <DialogTitle>{processDialog === "add" ? "Добавление нового пользователя" : "Удаление текущего пользователя"}</DialogTitle>
                <DialogContent>
                    {processDialog === "add" && <FormControl>
                        {addParams.map((param) => (
                            <TextField
                                margin="dense"
                                id={param.id}
                                label={param.name}
                                type={param.type}
                                required={param.required}
                                onChange={handleInput}
                            />
                        ))}
                    </FormControl>}
                    {processDialog === "remove" && <DialogContentText>
                        Вы действительно хотите удалить пользователя {personInfo?.name} {personInfo?.surname}?
                    </DialogContentText>}
                </DialogContent>
                <DialogActions>
                    <Button onClick={processDialog === "add" ? handleAddPerson : handleRemovePerson}>
                        Ок
                    </Button>
                    <Button onClick={handleCloseDialog}>
                        Отмена
                    </Button>
                </DialogActions>
            </Dialog>
            <ImportExport action={action} setAction={setAction} dbs={databases} />
        </>
    );
}

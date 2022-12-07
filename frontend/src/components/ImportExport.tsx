import React, {useState} from "react";
import axios from "axios";
import Alert from "@mui/material/Alert";
import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import Snackbar from "@mui/material/Snackbar";

interface IProps {
    action: string | null,
    setAction: (data: string | null) => void,
    dbs: {
        id: string,
        name: string
    }[]
}

enum Action {
    IMPORT = "import",
    EXPORT = "export"
}

enum ID {
    WATCHERS = "watchers", 
    PEOPLE = "people",
    OFFICES = "offices", 
    PROJECTS = "projects",
    DOCUMENTS = "documents"
}

enum ResultStatus {
    SUCCESS = "success",
    WARNING = "warning",
    ERROR = "error"
}

export function ImportExport({action, setAction, dbs}: IProps) {
    const [chosenFileName, setChosenFileName] = useState<string>("");
    const [data, setData] = useState<string | ArrayBuffer | null>(null);
    const [resultStatus, setResultStatus] = useState<ResultStatus | null>(null);

    const handleChooseFile = (event: React.ChangeEvent<HTMLInputElement>) => {
        setChosenFileName(event.target.files ? event.target.files[0].name : "");
        const reader = new FileReader();
        if (event.target.files) reader.readAsText(event.target.files[0]);
        reader.onload = () => {
            setData(reader.result);
        } 
    }

    const handleAction = (index: number) => {
        let path = "";
        switch (dbs[index].id) {
            case ID.WATCHERS:
                path = `${process.env.REACT_APP_WATCHER_HOST}${process.env.REACT_APP_ADMIN_API}`
                path += action === Action.IMPORT ? `${process.env.REACT_APP_IMPORT_WATCHERS}` : `${process.env.REACT_APP_EXPORT_WATCHERS}`;
                break;
            case ID.PEOPLE:
                path = `${process.env.REACT_APP_PERSON_HOST}${process.env.REACT_APP_ADMIN_API}`
                path += action === Action.IMPORT ? `${process.env.REACT_APP_IMPORT_PEOPLE}` : `${process.env.REACT_APP_EXPORT_PEOPLE}`;
                break;
            case ID.OFFICES:
                path = `${process.env.REACT_APP_PERSON_HOST}${process.env.REACT_APP_ADMIN_API}`
                path += action === Action.IMPORT ? `${process.env.REACT_APP_IMPORT_OFFICES}` : `${process.env.REACT_APP_EXPORT_OFFICES}`;
                break;
            case ID.PROJECTS:
                path = `${process.env.REACT_APP_PERSON_HOST}${process.env.REACT_APP_ADMIN_API}`
                path += action === Action.IMPORT ? `${process.env.REACT_APP_IMPORT_PROJECTS}` : `${process.env.REACT_APP_EXPORT_PROJECTS}`;
                break;
            case ID.DOCUMENTS:
                path = `${process.env.REACT_APP_DOCUMENT_HOST}${process.env.REACT_APP_ADMIN_API}`
                path += action === Action.IMPORT ? `${process.env.REACT_APP_IMPORT_DOCUMENTS}` : `${process.env.REACT_APP_EXPORT_DOCUMENTS}`;
                break;
        }
        
        if (action === Action.IMPORT) {
            axios.post(path, data, {
                headers: {
                    Authorization: `Bearer ${localStorage.accessToken}`,
                    "Content-Type": "application/json"
                }
            }).then(response => {
                if (response.data.actual === 0) {
                    setResultStatus(ResultStatus.ERROR);
                } else if (response.data.actual === response.data.expected) {
                    setResultStatus(ResultStatus.SUCCESS);
                } else {
                    setResultStatus(ResultStatus.WARNING);
                }
            }).catch(() => {
                setResultStatus(ResultStatus.ERROR);
            });
        } else {
            axios.get(path, {
                headers: {
                    Authorization: `Bearer ${localStorage.accessToken}`,
                    "Content-Type": "application/json"
                }
            }).then(response => {
                const href = "data:application/json;charset=utf-8," + encodeURIComponent(JSON.stringify(response.data));
                const link = document.createElement('a');
                link.href = href;
                link.download = `${dbs[index].id}.json`
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);
                URL.revokeObjectURL(href);
                setResultStatus(ResultStatus.SUCCESS);
            }).catch(() => {
                setResultStatus(ResultStatus.ERROR);
            });
        }

        handleClose();
    }

    const handleClose = () => {
        setChosenFileName("");
        setData(null);
        setAction(null);
    }

    const handleAlertClose = () => {
        setResultStatus(null);
    }

    return (
        <>
            {action === Action.IMPORT && <Dialog sx={{}} open={action !== null} onClose={handleClose}>
                <DialogTitle>Импорт {chosenFileName}</DialogTitle>
                <DialogContent>
                    {chosenFileName === "" ? <Button variant="text" component="label">
                        Выбрать файл
                        <input type="file" hidden onChange={handleChooseFile} />
                    </Button> : dbs.map((db, index) => (
                        <Button variant="text" onClick={() => {if (chosenFileName !== "") {handleAction(index)}}}>{db.name}</Button>
                    ))}
                </DialogContent>
            </Dialog>}
            {action === Action.EXPORT && <Dialog open={action !== null} onClose={handleClose}>
                <DialogTitle>Экспорт</DialogTitle>
                <DialogContent>
                    {dbs.map((db, index) => (
                        <Button variant="text" onClick={() => {handleAction(index)}}>{db.name}</Button>
                    ))}
                </DialogContent>
            </Dialog>}
            <Snackbar open={resultStatus === ResultStatus.SUCCESS} autoHideDuration={3000} onClose={handleAlertClose}>
                <Alert onClose={handleAlertClose} severity="success" sx={{ width: '100%' }}>
                    Успешно!
                </Alert>
            </Snackbar>
            <Snackbar open={resultStatus === ResultStatus.ERROR} autoHideDuration={3000} onClose={handleAlertClose}>
                <Alert onClose={handleAlertClose} severity="error" sx={{ width: '100%' }}>
                    Ошибка!
                </Alert>
            </Snackbar>
            <Snackbar open={resultStatus === ResultStatus.WARNING} autoHideDuration={3000} onClose={handleAlertClose}>
                <Alert onClose={handleAlertClose} severity="warning" sx={{ width: '100%' }}>
                    Не все записи добавлены
                </Alert>
            </Snackbar>
        </>
    );
}

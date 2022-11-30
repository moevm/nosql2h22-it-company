import React, {useState} from "react";
import {Checkbox} from "@mui/material";
import Grid from "@mui/material/Grid";
import FormControl from "@mui/material/FormControl";
import FormControlLabel from "@mui/material/FormControlLabel";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import {DocumentAllList} from "./DocumentAllList";

export function DocumentAllInfo() {
    const [sortRequest, setSortRequest] = useState<string>("date,asc");
    const [typeRequest, setTypeRequest] = useState<string[]>(["INCOME_STATEMENT", "WORK_STATEMENT"]);
    const [statusRequest, setStatusRequest] = useState<string[]>(["WORKING", "ON_HOLIDAY", "SEEK_LEAVE", "NOT_WORKING"]);

    const handleSort = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSortRequest((event.target as HTMLInputElement).value);
    }

    const handleType = (event: React.ChangeEvent<HTMLInputElement>) => {
        let type = event.target.value;
        if (typeRequest.indexOf(type) == -1) {
            setTypeRequest([...typeRequest, type]);
        } else {
            setTypeRequest(typeRequest.filter((str: string) => str !== type));
        }
        console.log(typeRequest)
    }

    const handleStatus = (event: React.ChangeEvent<HTMLInputElement>) => {
        let status = event.target.value;
        if (statusRequest.indexOf(status) == -1) {
            setStatusRequest([...statusRequest, status]);
        } else {
            setStatusRequest(statusRequest.filter((str: string) => str !== status));
        }
        console.log(statusRequest)
    }

    return (
        <>
            <Grid container spacing={2}>
                <Grid item xs={6}>
                    <FormControl>
                        <RadioGroup
                            defaultValue="date,asc"
                            name="document_sort"
                            onChange={handleSort}
                        >
                            <FormControlLabel value="date,asc" control={<Radio />} label="По дате заказа (от самых старых)" />
                            <FormControlLabel value="date,desc" control={<Radio />} label="По дате заказа (от самых новых)" />
                            <FormControlLabel value="status,asc" control={<Radio />} label="По статусу" />
                        </RadioGroup>
                    </FormControl>
                </Grid>
                <Grid item xs={6}>
                    <FormControl>
                        <FormControlLabel control={<Checkbox defaultChecked value="INCOME_STATEMENT" onChange={handleType} />} label="Отчёт о доходах" />
                        <FormControlLabel control={<Checkbox defaultChecked value="WORK_STATEMENT" onChange={handleType} />} label="Отчёт об отработанных днях" />
                    </FormControl>
                    <form>
                        <FormControl>
                            <FormControlLabel control={<Checkbox defaultChecked value="WORKING" onChange={handleStatus} />} label="Работает" />
                            <FormControlLabel control={<Checkbox defaultChecked value="ON_HOLIDAY" onChange={handleStatus} />} label="В отпуске" />
                            <FormControlLabel control={<Checkbox defaultChecked value="SEEK_LEAVE" onChange={handleStatus} />} label="На больничном" />
                            <FormControlLabel control={<Checkbox defaultChecked value="NOT_WORKING" onChange={handleStatus} />} label="Уволен" />
                        </FormControl>
                    </form>
                </Grid>
            </Grid>
            <DocumentAllList sortRequest={sortRequest} typeRequest={typeRequest.join(',')} statusRequest={statusRequest.join(',')} />
        </>
    );
}
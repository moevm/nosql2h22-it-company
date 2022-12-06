import React, {useEffect, useState} from "react";
import axios from "axios";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Paper from "@mui/material/Paper";
import Select, {SelectChangeEvent} from "@mui/material/Select";
import Stack from "@mui/material/Stack";
import {styled} from "@mui/material/styles";
import {ImportExport} from "./ImportExport";
import {Footer} from "./Footer";
import {WATCHER_PAGE_TITLE} from "../constants";

interface ITask {
    id: string,
    date: string,
    projectId: string,
    minutesAmount: number,
    comment: string
}

const Item = styled(Paper)(({theme}) => ({
    backgroundColor: "#EFEFEF",
    ...theme.typography.body2,
    padding: theme.spacing(3),
    borderRadius: "20px"
}));

export function WatcherModule() {
    const [week, setWeek] = useState<string[]>(["2022-11-28", "2022-12-04"]);
    const [weekLabel, setWeekLabel] = useState<string>("2022-11-28 - 2022-12-04");
    const [tasks, setTasks] = useState<ITask[]>([]);
    const [action, setAction] = useState<string | null>(null);

    const getTasks = () => {
        axios.get<ITask[]>(`${process.env.REACT_APP_WATCHER_HOST}/watcher/all-own-between-dates`, {
            headers: {
                Authorization: `Bearer ${localStorage.accessToken}`
            },
            params: {
                from: week[0],
                to: week[1]
            }
        }).then(response => {
            setTasks(response.data);
        });
    }

    useEffect(() => {
        document.title = WATCHER_PAGE_TITLE;
        getTasks();
    }, []);

    useEffect(() => {
        getTasks();
    }, [week]);

    const handleChange = (event: SelectChangeEvent) => {
        const localNewWeek = event.target.value
        setWeekLabel(localNewWeek);
        setWeek(localNewWeek.split(" - "));
    }

    return (
        <>
            <FormControl fullWidth sx={{marginTop: "1rem"}}>
                <InputLabel >Неделя:</InputLabel>
                <Select
                    value={weekLabel}
                    label="Week"
                    onChange={handleChange}
                >
                    <MenuItem value="2022-11-21 - 2022-11-27">2022.11.21 - 2022.11.27</MenuItem>
                    <MenuItem value="2022-11-28 - 2022-12-04">2022.11.28 - 2022.12.04</MenuItem>
                    <MenuItem value="2022-12-05 - 2022-12-11">2022.12.05 - 2022.12.11</MenuItem>
                </Select>
            </FormControl>
            <Stack spacing={2}>
                {tasks.map((task) => (
                    <Item elevation={6}>
                        Дата: {task.date.substring(0, 10)}
                        <br/>
                        Часы: {(task.minutesAmount / 60).toFixed(2)}
                        <br/>
                        Комментарий: {task.comment}
                    </Item>
                ))}
            </Stack>
            <ImportExport action={action} setAction={setAction} dbs={[{id: "watchers", name: "Списанные часы"}]} />
            <Footer handle={(num: number) => {}} setAction={setAction} />
        </>
    );
}

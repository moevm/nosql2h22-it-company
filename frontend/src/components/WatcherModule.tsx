import React, {useEffect, useState} from "react";
import axios from "axios";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select, {SelectChangeEvent} from "@mui/material/Select";
import {WATCHER_PAGE_TITLE} from "../constants";
import Stack from "@mui/material/Stack";
import {styled} from "@mui/material/styles";
import Paper from "@mui/material/Paper";

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
    const [weekLabel, setWeekLabel] = useState<string>("2022.11.28 - 2022.12.04");
    const [tasks, setTasks] = useState<ITask[]>([]);

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

    const handleChange = (event: SelectChangeEvent) => {
        setWeekLabel(event.target.value);
        setWeek(weekLabel.split(" - "));
        getTasks();
    }

    return (
        <>
            <FormControl fullWidth>
                <InputLabel>Неделя:</InputLabel>
                <Select
                    value={weekLabel}
                    label="Week"
                    onChange={handleChange}
                >
                    <MenuItem value="2022.11.21 - 2022.11.27">2022.11.21 - 2022.11.27</MenuItem>
                    <MenuItem value="2022.11.28 - 2022.12.04">2022.11.28 - 2022.12.04</MenuItem>
                    <MenuItem value="2022.12.05 - 2022.12.11">2022.12.05 - 2022.12.11</MenuItem>
                </Select>
            </FormControl>
            <Stack spacing={2}>
                {tasks.map((task) => (
                    <Item elevation={6}>
                        {task.date}
                        <br/>
                        Часы: {(task.minutesAmount / 60).toFixed(2)}
                        <br/>
                        Комментарий: {task.comment}
                    </Item>
                ))}
            </Stack>
        </>
    );
}
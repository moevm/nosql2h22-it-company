import React, {useEffect, useState} from "react";
import {WATCHER_PAGE_TITLE} from "../constants";
import {ImportExport} from "./ImportExport";
import {Footer} from "./Footer";
import Stack from "@mui/material/Stack";
import {styled} from "@mui/material/styles";
import Paper from "@mui/material/Paper";
import CustomCalendar from "./CustomCalendar";
import dayjs, {Dayjs} from "dayjs";
import RuDayJsLocale from 'dayjs/locale/ru';
import {DATE_FORMAT, DAY_NAME_FORMAT, DAY_UNIT, SHORT_DAY_NAME_FORMAT} from "../constants/dateConstants";
import {getInclusiveDatesRange, getWeekBounds} from "../util/dateUtil";
import _ from "lodash";
import {
    Autocomplete,
    Box,
    Button,
    capitalize,
    FormControl,
    InputLabel,
    MenuItem,
    Modal,
    Select,
    SelectChangeEvent,
    TextField,
    Typography
} from "@mui/material";
import {getAllWatcherRecordsBetweenDates, saveWatcherRecord} from "../requests/watcherHttpRequests";
import {getWorkTimeByMinutes, getWorkTimes, WorkTime} from "../util/timeUtil";
import {FULL_DAY_WORK_TIME_IN_MINUTES} from "../constants/timeConstants";
import {getAllProjects} from "../requests/projectHttpRequests";

dayjs.locale(RuDayJsLocale.name);

interface IGroupedTasks {
    date: Dayjs,
    tasks: IWatcherRecord[]
}

const Item = styled(Paper)(({theme}) => ({
    backgroundColor: "#EFEFEF",
    ...theme.typography.body2,
    padding: theme.spacing(3),
    borderRadius: "20px"
}));

export function WatcherModule() {
    const now = dayjs()
    const [start, end] = getWeekBounds(now)

    const [week, setWeek] = useState<Dayjs[]>([start, end]);
    const [groupedTasks, setGroupedTasks] = useState<IGroupedTasks[]>([]);
    const [projects, setProjects] = useState<IProject[]>([]);
    const [action, setAction] = useState<string | null>(null);

    const getTasks = () => {
        const from = week[0].format(DATE_FORMAT)
        const to = week[1].format(DATE_FORMAT)
        const ONE_HUNDRED_WATCHER_RECORDS = 100

        getAllWatcherRecordsBetweenDates(from, to, ONE_HUNDRED_WATCHER_RECORDS)
            .then(response => {
                const tasks: IWatcherRecord[] = response.data;
                const groupedTasks: IGroupedTasks[] = _.chain(tasks)
                    .groupBy(task => task.date.substring(0, 10))
                    .map((value, key) => ({
                        date: dayjs(key.substring(0, 10)),
                        tasks: value
                    })).value()

                setGroupedTasks(groupedTasks);
            });
    }

    const getProjects = () => {
        const TWO_HUNDRED_PROJECTS = 200
        getAllProjects(TWO_HUNDRED_PROJECTS)
            .then((response: { data: IProject[] }) => {
                setProjects(response.data)
            })
    }

    useEffect(() => {
        document.title = WATCHER_PAGE_TITLE;
        getTasks();
        getProjects();
    }, []);

    useEffect(() => {
        getTasks();
        getProjects();
    }, [week]);

    const handleChange = (date: Dayjs | null) => {
        if (date === null) return

        setWeek(getWeekBounds(date));
    }

    const minutesToHours = (minutes: number): string => {
        return (minutes / 60).toFixed(2)
    }

    const countHoursInDay = (tasks: IWatcherRecord[]): string => {
        const totalMinutes = tasks.map(task => task.minutesAmount)
            .reduce((acc, task) => acc + task)

        return minutesToHours(totalMinutes)
    }

    interface IWatcherDayListItemProps {
        task: IWatcherRecord
    }

    const WatcherDayListItem = ({task}: IWatcherDayListItemProps): JSX.Element => {
        const emptyProject = {id: "", name: ""}
        const [project, setProject] = useState<IProject>(emptyProject);

        const getProjectById = (id: string) => {
            return projects.find((localProject: IProject) => localProject.id === id) ?? emptyProject
        }

        if (project === emptyProject) {
            setProject(getProjectById(task.projectId))
        }


        return (<Item elevation={6}>
            <Typography variant="h6">{project.name}</Typography>
            Часы: {minutesToHours(task.minutesAmount)}
            <br/>
            Комментарий: {task.comment}
        </Item>)
    }

    interface IWatcherDayListModalProps {
        open: boolean,
        date: Dayjs
        setOpen: (b: boolean) => void,
        groupedTask: IGroupedTasks | undefined
    }

    const WatcherDayListModal = ({open, date, setOpen, groupedTask}: IWatcherDayListModalProps): JSX.Element => {
        const handleClose = (): void => setOpen(false);

        const style = {
            position: 'absolute' as 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            width: 500,
            bgcolor: 'background.paper',
            border: '2px solid #000',
            boxShadow: 24,
            p: 4,
        };

        return <Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
        >
            <Box sx={style}>

                <Typography id="modal-modal-title" variant="h6" component="h2">
                    Записи — {capitalize(date.format(DAY_NAME_FORMAT))}
                </Typography>

                <Box id="modal-modal-description">
                    {groupedTask && groupedTask.tasks.map((task: IWatcherRecord) =>
                        <WatcherDayListItem task={task}/>
                    )}
                </Box>
            </Box>
        </Modal>
    }

    interface IWatcherDayAddModalProps {
        open: boolean,
        date: Dayjs
        setOpen: (b: boolean) => void,
    }

    interface IProjectSelectProps {
        projectId: string
        setProjectId: (projectId: string) => void
    }

    const ProjectSelect = ({projectId, setProjectId}: IProjectSelectProps): JSX.Element => {

        const handleChange = (event: SelectChangeEvent) => setProjectId(event.target.value);

        return (<FormControl sx={{m: 1, minWidth: 120}}>
            <InputLabel id="demo-simple-select-helper-label">Проект</InputLabel>
            <Select
                id="demo-simple-select-helper"
                label="Проект"
                value={projectId}
                onChange={handleChange}
            >
                {projects.map((project: IProject) => (
                    <MenuItem value={project.id} key={project.id}>{project.name}</MenuItem>
                ))}
            </Select>
        </FormControl>)
    }


    const WatcherDayAddModal = ({open, date, setOpen}: IWatcherDayAddModalProps): JSX.Element => {
        const [comment, setComment] = useState<string>("");
        const [projectId, setProjectId] = useState<string>(projects[0]?.id ?? "")
        const defaultWorkTime = getWorkTimeByMinutes(FULL_DAY_WORK_TIME_IN_MINUTES)
        const [workTime, setWorkTime] = useState<WorkTime>(defaultWorkTime)

        const handleClose = (): void => setOpen(false);

        const handleComment = (event: React.ChangeEvent<HTMLDivElement>): void => {
            setComment((event.target as HTMLInputElement).value)
        }

        const handleWorkTime = (event: any, newValue: WorkTime | null): void => {
            setWorkTime(newValue ?? defaultWorkTime)
        }

        const handleWorkTimesEquality = (option: WorkTime, value: WorkTime) => {
            return option.name === value.name && option.minutes === value.minutes
        }

        const workTimes = _.sortBy(getWorkTimes()).reverse()
        const getWorkTimeLabel = (workTimes: WorkTime): string => workTimes.name

        const handleSubmit = () => {
            if (comment) {
                saveWatcherRecord({
                    date: date.format(DATE_FORMAT),
                    comment: comment,
                    projectId: projectId,
                    minutesAmount: workTime.minutes
                }).then(() => {
                        getTasks();
                        handleClose();
                    }
                )
            }
        }

        const style = {
            position: 'absolute' as 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            width: 500,
            bgcolor: 'background.paper',
            border: '2px solid #000',
            boxShadow: 24,
            p: 4,
        };

        return (<Modal
            open={open}
            onClose={handleClose}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
        >
            <Box sx={style}>

                <Typography id="modal-modal-title" variant="h6" component="h2">
                    Добавление записи — {capitalize(date.format(DAY_NAME_FORMAT))}
                </Typography>

                <Stack id="modal-modal-description" spacing={2} sx={{marginX: "1rem"}}>
                    <ProjectSelect projectId={projectId} setProjectId={setProjectId}/>

                    <Autocomplete
                        options={workTimes}
                        value={workTime}
                        onChange={handleWorkTime}
                        getOptionLabel={getWorkTimeLabel}
                        isOptionEqualToValue={handleWorkTimesEquality}
                        renderInput={(params) => <TextField {...params} label="Часы"/>}
                    />

                    <TextField
                        id="outlined-multiline-static"
                        multiline
                        maxRows={6}
                        label="Комментарий"
                        placeholder="Сегодня был увлекательный день..."
                        variant="outlined"
                        onInput={handleComment}
                    />

                    <Button
                        variant="outlined"
                        fullWidth={true}
                        onClick={handleSubmit}
                    >
                        Отправить
                    </Button>
                </Stack>


            </Box>
        </Modal>)
    }

    interface IWatcherDayProps {
        date: Dayjs,
        groupedTask: IGroupedTasks | undefined
    }

    const WatcherDay = ({date, groupedTask}: IWatcherDayProps): JSX.Element => {
        const [openDetailedInfo, setOpenDetailedInfo] = useState<boolean>(false);
        const handleOpenDetailedInfo = (): void => setOpenDetailedInfo(true);

        const [openAddForm, setOpenAddForm] = useState<boolean>(false);
        const handleOpenAddForm = (): void => setOpenAddForm(true);

        const ZERO_HOURS_IN_DAY = "0.00"


        return (<Item elevation={6}>
            Дата: {date.format(DATE_FORMAT)}, {date.format(SHORT_DAY_NAME_FORMAT)}
            <br/>
            Часы: {groupedTask?.tasks ? countHoursInDay(groupedTask.tasks) : ZERO_HOURS_IN_DAY}
            <br/>

            <Button
                variant="outlined"
                fullWidth={true}
                onClick={handleOpenAddForm}
            >
                +
            </Button>

            <Button
                variant="outlined"
                fullWidth={true}
                onClick={handleOpenDetailedInfo}
            >
                Подробнее
            </Button>

            <WatcherDayListModal
                open={openDetailedInfo}
                setOpen={setOpenDetailedInfo}
                date={date}
                groupedTask={groupedTask}
            />

            <WatcherDayAddModal
                open={openAddForm}
                setOpen={setOpenAddForm}
                date={date}
            />
        </Item>);
    }

    const WatcherDaysList = (): JSX.Element => {
        const [start, end] = week
        const dates = getInclusiveDatesRange(start, end);

        const findGroupedTaskByDate = (date: Dayjs) => {
            return groupedTasks.find((groupedTask: IGroupedTasks) => (groupedTask.date.isSame(date, DAY_UNIT)))
        }

        return (
            <>
                {dates.map(
                    (date: Dayjs) => (
                        <WatcherDay date={date} groupedTask={findGroupedTaskByDate(date)}/>
                    )
                )}
            </>
        );
    }

    return (
        <>
            <Box sx={{margin: "1rem"}}>
                <CustomCalendar
                    onChange={handleChange}
                />
            </Box>
            <Stack direction={"row"} spacing={2} sx={{marginX: "1rem"}}>
                <WatcherDaysList/>
            </Stack>
            <ImportExport action={action} setAction={setAction} dbs={[{id: "watchers", name: "Списанные часы"}]}/>
            <Footer handle={(num: number) => {
            }} setAction={setAction}/>
        </>
    );
}

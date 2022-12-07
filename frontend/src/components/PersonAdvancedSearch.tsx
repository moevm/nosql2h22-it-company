import React, {useState} from "react";
import FormControl from "@mui/material/FormControl";
import Grid from "@mui/material/Grid";
import MenuItem from "@mui/material/MenuItem";
import OutlinedInput from "@mui/material/OutlinedInput";
import Paper from "@mui/material/Paper";
import Slider from "@mui/material/Slider";
import Stack from "@mui/material/Stack";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import {ThemeProvider} from "@mui/material";
import {styled} from "@mui/material/styles";
import personAdvancedSearchTheme from "../themes/PersonAdvancedSearchTheme";
import {PersonList} from "./PersonList";
import {PERSON_ADVANCED_SEARCH_ENUMS} from "../constants";
import {getPersonWithAdvancedSearch} from "../requests/personHttpRequests";

interface IProps {
    setPerson: (data: IPerson) => void,
    setAdvancedSearch: (data: boolean) => void
}

interface IRequest {
    name: string,
    surname: string,
    patronymic: string,
    sex: string,
    position: string,
    status: string,
    age: number[]
}

const Item = styled(Paper)(({theme}) => ({
    backgroundColor: "transparent",
    elevation: 0,
    ...theme.typography.body2,
    padding: theme.spacing(3)
}));

export function PersonAdvancedSearch({setPerson, setAdvancedSearch}: IProps) {
    const [request, setRequest] = useState<IRequest>({
        name: "",
        surname: "",
        patronymic: "",
        sex: "",
        position: "",
        status: "",
        age: [18, 100]
    });
    const [persons, setPersons] = useState<IPerson[]>([]);

    const getSearchResult = () => {
        getPersonWithAdvancedSearch(
            {
                name: request.name.trim() === "" ? undefined : request.name,
                surname: request.surname.trim() === "" ? undefined : request.surname,
                patronymic: request.surname.trim() === "" ? undefined : request.surname,
                sex: (request.sex.trim() === "") ? undefined : request.sex,
                position: request.position.trim() === "" ? undefined : request.position,
                status: request.status.trim() === "" ? undefined : request.status,
                start_age: request.age[0],
                end_age: request.age[1]
            }
        ).then(response => {
            setPersons(response.data);
        });
    }

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        getSearchResult();
    }

    const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
        setRequest({
            ...request,
            [event.target.name]: event.target.value
        });
        getSearchResult();
    }

    const handleChange = (event: Event, newValue: number | number[]) => {
        setRequest({
            ...request,
            age: newValue as number[]
        });
        getSearchResult();
    };

    return (
        <ThemeProvider theme={personAdvancedSearchTheme}>
            <Grid container spacing={4}>
                <Grid item xs={6}>
                    <Stack spacing={2}>
                        {[["name", "Имя"], ["surname", "Фамилия"], ["patronymic", "Отчество"]].map((item) => (
                            <Item elevation={0}>
                                <Grid container spacing={2}>
                                    <Grid item xs={4}>
                                        <Typography variant="h6" component="span">
                                            {item[1]}:
                                        </Typography>
                                    </Grid>
                                    <Grid item xs={8}>
                                        <form onSubmit={handleSubmit}>
                                            <FormControl fullWidth>
                                                <OutlinedInput
                                                    name={item[0]}
                                                    type="text"
                                                    onChange={handleInput}
                                                />
                                            </FormControl>
                                        </form>
                                    </Grid>
                                </Grid>
                            </Item>
                        ))}
                        {PERSON_ADVANCED_SEARCH_ENUMS.map((item) => (
                            <Item elevation={0}>
                                <Grid container spacing={2}>
                                    <Grid item xs={4}>
                                        <Typography variant="h6" component="span">
                                            {item.value}:
                                        </Typography>
                                    </Grid>
                                    <Grid item xs={8}>
                                        <form onSubmit={handleSubmit}>
                                            <FormControl fullWidth>
                                                <TextField
                                                    name={item.name}
                                                    type="text"
                                                    select
                                                    onChange={handleInput}
                                                    variant="outlined"
                                                >
                                                    {item.enum.map((option) => (
                                                        <MenuItem key={option.name} value={option.name}>
                                                            {option.value}
                                                        </MenuItem>
                                                    ))}
                                                </TextField>
                                            </FormControl>
                                        </form>
                                    </Grid>
                                </Grid>
                            </Item>
                        ))}
                        <Item elevation={0}>
                            <Grid container spacing={2}>
                                <Grid item xs={4}>
                                    <Typography variant="h6" component="span">
                                        Возраст:
                                    </Typography>
                                </Grid>
                                <Grid item xs={8}>
                                    <Slider
                                        getAriaLabel={() => 'Age range'}
                                        value={request.age}
                                        min={18}
                                        step={1}
                                        max={100}
                                        onChange={handleChange}
                                        valueLabelDisplay="auto"
                                        getAriaValueText={(value: number) => {
                                            return `${value}`
                                        }}
                                    />
                                </Grid>
                            </Grid>
                        </Item>
                    </Stack>
                </Grid>
                <Grid item xs={6}>
                    <PersonList persons={persons} setPerson={setPerson} setAdvancedSearch={setAdvancedSearch}/>
                </Grid>
            </Grid>
        </ThemeProvider>
    );
}

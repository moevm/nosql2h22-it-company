import React, {useRef, useState} from "react";
import axios from "axios";
import Container from "@mui/material/Container";
import FormControl from "@mui/material/FormControl";
import IconButton from "@mui/material/IconButton";
import InputAdornment from "@mui/material/InputAdornment";
import ListItemText from "@mui/material/ListItemText";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import OutlinedInput from "@mui/material/OutlinedInput";
import {SearchOutlined, Tune} from "@mui/icons-material";
import {ThemeProvider} from "@mui/material";
import personSearchTheme from "../themes/PersonSearchTheme";
import {IPerson} from "../models";

interface IProps {
    setPerson: (data: IPerson) => void,
    setAdvancedSearch: (data: boolean) => void
}

export function PersonSearch({setPerson, setAdvancedSearch}: IProps) {
    const [search, setSearch] = useState<string>("");
    const [persons, setPersons] = useState<IPerson[]>([]);
    const [searchAnchorElement, setSearchAnchorElement] = useState<null | HTMLElement>(null);

    const anchor = useRef(null);

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        axios.get<IPerson[]>(`${process.env.REACT_APP_PERSON_HOST}/person`, {
            headers: {
                Authorization: `Bearer ${localStorage.accessToken}`
            },
            params: {
                name: search.trim() === "" ? undefined : search
            }
        }).then(request => {
            setPersons(request.data);
            setSearchAnchorElement(anchor.current);
        });
    }

    const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
        setSearch(event.target.value);
    }

    const handleClickAdvancedSearch = () => {
        setAdvancedSearch(true);
    }

    const handleMouseDownAdvancedSearch = (event: React.MouseEvent) => {
        event.preventDefault();
    }

    const handleCloseSearch = () => {
        setSearchAnchorElement(null);
    }

    const handleClickPerson = (person: IPerson) => {
        setSearchAnchorElement(null);
        setPerson(person);
    }

    return (
        <ThemeProvider theme={personSearchTheme}>
            <Container>
                <form onSubmit={handleSubmit} ref={anchor}>
                    <FormControl fullWidth>
                        <OutlinedInput
                            name="search"
                            required
                            type="text"
                            onChange={handleInput}
                            startAdornment={
                                <InputAdornment position="start">
                                    <SearchOutlined />
                                </InputAdornment>
                            }
                            endAdornment={
                                <InputAdornment position="end">
                                    <IconButton
                                        onClick={handleClickAdvancedSearch}
                                        onMouseDown={handleMouseDownAdvancedSearch}
                                        edge="end"
                                    >
                                        <Tune />
                                    </IconButton>
                                </InputAdornment>
                            }
                        />
                    </FormControl>
                </form>
                <Menu
                    anchorEl={searchAnchorElement}
                    open={searchAnchorElement !== null}
                    onClose={handleCloseSearch}
                >
                    {persons.map((person) => (
                        <MenuItem key={person.id} onClick={() => handleClickPerson(person)}>
                            <ListItemText> {person.name} {person.surname} </ListItemText>
                        </MenuItem>
                    ))}
                </Menu>
            </Container>
        </ThemeProvider>
    );
}
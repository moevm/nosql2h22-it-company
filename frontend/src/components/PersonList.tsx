import React, {useState} from "react";
import Avatar from "@mui/material/Avatar";
import Paper from "@mui/material/Paper";
import Stack from "@mui/material/Stack";
import Typography from "@mui/material/Typography";
import {IPerson} from "../models";
import {styled} from "@mui/material/styles";

interface IProps {
    persons: IPerson[],
    setPerson: (data: IPerson) => void,
    setAdvancedSearch: (data: boolean) => void
}

const Item = styled(Paper)(({theme}) => ({
    backgroundColor: "#EFEFEF",
    ...theme.typography.body2,
    padding: theme.spacing(3),
    borderRadius: "20px"
}));

export function PersonList({persons, setPerson, setAdvancedSearch}: IProps) {
    const [hover, setHover] = useState<boolean>(false);

    const handleClick = (person: IPerson) => {
        setPerson(person);
        setAdvancedSearch(false);
    }

    return (
        <>
            <Stack spacing={2}>
                {persons.map((person) => (
                    <Item
                        elevation={6}
                        sx={{cursor: hover ? 'pointer' : 'default'}}
                        onClick={() => handleClick(person)}
                        onMouseEnter={() => setHover(true)}
                        onMouseLeave={() => setHover(false)}
                    >
                        <Stack direction="row" sx={{}}>
                            <Avatar src="/broken-image.jpg" sx={{width: '50px', height: '50px', marginRight: '1rem'}} />
                            <div>
                                <Typography variant="body1" component="p">
                                    {person.name} {person.surname}
                                </Typography>
                                <Typography variant="body1" component="p">
                                    {person.position}
                                </Typography>
                            </div>
                        </Stack>
                    </Item>
                ))}
            </Stack>
        </>
    );
}
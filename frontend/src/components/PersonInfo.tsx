import React from "react";
import Avatar from "@mui/material/Avatar";
import Paper from "@mui/material/Paper";
import Stack from "@mui/material/Stack";
import Typography from "@mui/material/Typography";
import {ThemeProvider} from "@mui/material";
import {styled} from "@mui/material/styles";
import personInfoTheme from "../themes/PersonInfoTheme";

interface IProps {
    person: IPerson | null
}

const Item = styled(Paper)(({theme}) => ({
    backgroundColor: "#EFEFEF",
    ...theme.typography.body2,
    padding: theme.spacing(3),
    borderRadius: "20px",
    elevation: 6
}));

export function PersonInfo({person}: IProps) {
    return (
        <ThemeProvider theme={personInfoTheme}>
            <Stack spacing={2}>
                <Item sx={{width: "40%"}}>
                    <Stack direction="row" sx={{}}>
                        <Avatar src="/broken-image.jpg" sx={{width: '75px', height: '75px', marginRight: '1rem'}} />
                        <div>
                            <Typography variant="body1" component="p">
                                {person?.name} {person?.surname}
                            </Typography>
                            <Typography variant="body1" component="p">
                                {person?.position}
                            </Typography>
                            <Typography variant="body1" component="p">
                                {person?.status}
                            </Typography>
                        </div>
                    </Stack>
                </Item>
                <Item>
                    Телефон: {person?.contacts.phoneNumber}
                </Item>
                <Item>
                    Email: {person?.contacts.email}
                </Item>
                <Item>
                    Время работы: {person?.jobTime.start} &ndash; {person?.jobTime.end}
                </Item>
                <Item>
                    Комментарий:
                    <Typography variant="body1" component="p">
                        {person?.comment}
                    </Typography>
                </Item>
            </Stack>
        </ThemeProvider>
    );
}
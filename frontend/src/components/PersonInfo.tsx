import React, {useEffect, useState} from "react";
import Avatar from "@mui/material/Avatar";
import Paper from "@mui/material/Paper";
import Stack from "@mui/material/Stack";
import Typography from "@mui/material/Typography";
import {ThemeProvider} from "@mui/material";
import {styled} from "@mui/material/styles";
import {useTypedSelector} from "../hooks/useTypedSelector";
import {PERSON_ADVANCED_SEARCH_ENUMS} from "../constants";
import personInfoTheme from "../themes/PersonInfoTheme";
import {calculateAge} from "../util/dateUtil";

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

enum UserRights {
    DEFAULT = "default",
    OWNER = "owner",
    ADVANCED = "advanced"
}

export function PersonInfo({person}: IProps) {
    const [rights, setRights] = useState<UserRights>(UserRights.DEFAULT);

    const userState: IUserState = useTypedSelector(state => state.user);

    useEffect(() => {
        if (userState?.advancedRole) {
            setRights(UserRights.ADVANCED);
        } else if (userState?.id === person?.id) {
            setRights(UserRights.OWNER);
        } else {
            setRights(UserRights.DEFAULT);
        }
    }, [person]);

    return (
        <ThemeProvider theme={personInfoTheme}>
            <Stack spacing={2}>
                <Item sx={{width: "40%"}}>
                    <Stack direction="row" sx={{}}>
                        <Avatar src="/broken-image.jpg" sx={{width: '75px', height: '75px', marginRight: '1rem'}} />
                        <div>
                            <Typography variant="body1" component="p">
                                {person?.name} {person?.surname}, {calculateAge(person?.birthday)}
                            </Typography>
                            <Typography variant="body1" component="p">
                                {PERSON_ADVANCED_SEARCH_ENUMS.find(item => item.name === "position")!!.enum.find(item => item.name === person?.position)?.value}
                            </Typography>
                            <Typography variant="body1" component="p">
                                {PERSON_ADVANCED_SEARCH_ENUMS.find(item => item.name === "status")!!.enum.find(item => item.name === person?.status)?.value}
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
                {rights !== UserRights.DEFAULT && <Item>
                    Паспорт: {person?.confidential.passportData.number}, выдал&nbsp;
                    {person?.confidential.passportData.issuedPlace} {person?.confidential.passportData.issuedDate}
                </Item>}
                {rights !== UserRights.DEFAULT && <Item>
                    Адрес: {person?.confidential.address}
                </Item>}
                {rights !== UserRights.DEFAULT && <Item>
                    Заработная плата: ₽{person?.confidential.salary}
                </Item>}
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
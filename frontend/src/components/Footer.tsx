import React, {useEffect, useState} from "react";
import jwt_decode from "jwt-decode";
import BottomNavigation from "@mui/material/BottomNavigation";
import BottomNavigationAction from "@mui/material/BottomNavigationAction";
import Container from "@mui/material/Container";
import {IToken} from "../models";

interface IProps {
    handle: (data: number) => void,
    children: JSX.Element[]
}

export function Footer({handle, children}: IProps) {
    const [extendedRights, setExtendedRights] = useState<boolean>(false);
    const [value, setValue] = useState<number>(0);

    useEffect(() => {
        let token: IToken = jwt_decode(localStorage.accessToken);
        setExtendedRights(token.role === "HR");
    }, []);

    return (
        <Container sx={{ position: 'fixed', bottom: 0, left: 0, right: 0 }}>
            {extendedRights && <BottomNavigation value={value} onChange={(event, newValue) => {
                setValue(newValue);
                handle(value);
            }}>
                {children.map((element: JSX.Element) => (
                    <BottomNavigationAction icon={element} />
                ))}
            </BottomNavigation>}
        </Container>
    );
}
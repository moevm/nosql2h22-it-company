import React, {useEffect, useState} from "react";
import BottomNavigation from "@mui/material/BottomNavigation";
import BottomNavigationAction from "@mui/material/BottomNavigationAction";
import Container from "@mui/material/Container";
import store from "../store";

interface IProps {
    handle: (data: number) => void,
    children: JSX.Element[]
}

export function Footer({handle, children}: IProps) {
    const [extendedRights, setExtendedRights] = useState<boolean>(false);
    const [value, setValue] = useState<number>(0);

    useEffect(() => {
        setExtendedRights(store.getState().user?.advancedRole ?? false);
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
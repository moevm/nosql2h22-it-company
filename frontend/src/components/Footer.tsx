import React, {useEffect, useState} from "react";
import ButtonGroup from "@mui/material/ButtonGroup";
import IconButton from "@mui/material/IconButton";
import Paper from "@mui/material/Paper";
import {FileDownloadOutlined, FileUploadOutlined} from "@mui/icons-material";
import {ThemeProvider} from "@mui/material";
import footerTheme from "../themes/FooterTheme";
import {useTypedSelector} from "../hooks/useTypedSelector";

interface IProps {
    colorable?: boolean,
    handle: (data: number) => void,
    setAction: (data: string | null) => void,
    children?: JSX.Element[]
}

enum Action {
    IMPORT = "import",
    EXPORT = "export"
}

export function Footer({colorable, handle, setAction, children}: IProps) {
    const [extendedRights, setExtendedRights] = useState<boolean>(false);
    const [value, setValue] = useState<number>(0);

    const userState: IUserState = useTypedSelector(state => state.user);

    useEffect(() => {
        setExtendedRights(userState?.advancedRole);
    }, [userState]);

    const handleClick = (index: number) => {
        setValue(index);
        handle(index ?? -1);
    }

    return (
        <ThemeProvider theme={footerTheme}>
            {extendedRights && <Paper component="footer" square>
                {children && <ButtonGroup>
                    {children.map((element: JSX.Element, index: number) => (
                        <IconButton onClick={() => {handleClick(index)}} sx={{color: (colorable && value === index) ? "#660708" : "black"}}>
                            {element}
                        </IconButton>
                    ))}
                </ButtonGroup>}
                <ButtonGroup>
                    <IconButton sx={{color: "black"}} onClick={() => {setAction(Action.IMPORT)}}>
                        <FileDownloadOutlined fontSize='large' />
                    </IconButton>
                    <IconButton sx={{color: "black"}} onClick={() => {setAction(Action.EXPORT)}}>
                        <FileUploadOutlined fontSize='large' />
                    </IconButton>
                </ButtonGroup>
            </Paper>}
        </ThemeProvider>
    );
}
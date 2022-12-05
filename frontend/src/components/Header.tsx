import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import AppBar from "@mui/material/AppBar";
import Avatar from "@mui/material/Avatar";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import Toolbar from "@mui/material/Toolbar";
import {ThemeProvider} from "@mui/material";
import {AccessTime, GroupsOutlined, TextSnippetOutlined} from "@mui/icons-material";
import headerTheme from "../themes/HeaderTheme";
import {USER_TOKEN} from "../constants";
import {useActions} from "../hooks/useAction";
import {useTypedSelector} from "../hooks/useTypedSelector";

export enum ActiveModule {
    NONE,
    WATCHER,
    PERSON,
    DOCUMENT
}

interface IActiveModule {
    activeModule: ActiveModule,
    setActiveModule: (data: ActiveModule) => void
}

const watcherButtonId = "watcher-button";
const personButtonId = "person-button";
const documentButtonId = "document-button";

export function Header({activeModule, setActiveModule}: IActiveModule) {
    const [quitAnchorElement, setQuitAnchorElement] = useState<null | HTMLElement>(null);

    const navigate = useNavigate();

    const {changeUser} = useActions();
    const userState: IUserState = useTypedSelector(state => state.user);

    const handleClickModule = (event: React.MouseEvent<HTMLDivElement, MouseEvent>) => {
        switch (event.currentTarget.id) {
            case watcherButtonId:
                setActiveModule(ActiveModule.WATCHER);
                break;
            case personButtonId:
                setActiveModule(ActiveModule.PERSON);
                break;
            case documentButtonId:
                setActiveModule(ActiveModule.DOCUMENT);
                break;
        }
    }

    const handleClickQuit = (event: React.MouseEvent<HTMLButtonElement>) => {
        setQuitAnchorElement(event.currentTarget);
    }

    const handleCloseQuit = () => {
        setQuitAnchorElement(null);
    }

    const handleQuit = () => {
        setQuitAnchorElement(null);
        localStorage.setItem(USER_TOKEN, "");
        changeUser();
        navigate(`${process.env.REACT_APP_AUTH_PAGE}`);
    }

    return (
        <ThemeProvider theme={headerTheme}>
            <AppBar position="static">
                <Toolbar disableGutters>
                    <Box>
                        <List>
                            <ListItem key="watcher" disablePadding>
                                <ListItemButton
                                    id={watcherButtonId}
                                    onClick={handleClickModule}
                                >
                                    <ListItemIcon sx={{color: (activeModule === ActiveModule.WATCHER ? "#660708" : "black")}}>
                                        <AccessTime fontSize='large' />
                                    </ListItemIcon>
                                    <ListItemText primary="Watcher" sx={{color: (activeModule === ActiveModule.WATCHER ? "#660708" : "black")}} />
                                </ListItemButton>
                            </ListItem>
                            <ListItem key="person" disablePadding>
                                <ListItemButton
                                    id={personButtonId}
                                    onClick={handleClickModule}
                                >
                                    <ListItemIcon sx={{color: (activeModule === ActiveModule.PERSON ? "#660708" : "black")}}>
                                        <GroupsOutlined fontSize='large' />
                                    </ListItemIcon>
                                    <ListItemText primary="Person" sx={{color: (activeModule === ActiveModule.PERSON ? "#660708" : "black")}} />
                                </ListItemButton>
                            </ListItem>
                            <ListItem key="document" disablePadding>
                                <ListItemButton
                                    id={documentButtonId}
                                    onClick={handleClickModule}
                                >
                                    <ListItemIcon sx={{color: (activeModule === ActiveModule.DOCUMENT ? "#660708" : "black")}}>
                                        <TextSnippetOutlined fontSize='large' />
                                    </ListItemIcon>
                                    <ListItemText primary="Document" sx={{color: (activeModule === ActiveModule.DOCUMENT ? "#660708" : "black")}} />
                                </ListItemButton>
                            </ListItem>
                        </List>
                    </Box>
                </Toolbar>
                <Button
                    id="quit-button"
                    variant="text"
                    endIcon={<Avatar src="/broken-image.jpg" />}
                    onClick={handleClickQuit}
                >
                    {userState?.name} {userState?.surname}
                </Button>
                <Menu
                    id="quit-menu"
                    anchorEl={quitAnchorElement}
                    open={quitAnchorElement !== null}
                    onClose={handleCloseQuit}
                    MenuListProps={{
                        'aria-labelledby': 'quit-button',
                    }}
                >
                    <MenuItem onClick={handleQuit}>
                        <ListItemText>Выход</ListItemText>
                    </MenuItem>
                </Menu>
            </AppBar>
        </ThemeProvider>
    );
}
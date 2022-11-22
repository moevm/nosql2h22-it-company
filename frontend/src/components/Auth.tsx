import React from "react";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import {ThemeProvider} from "@mui/material";
import {Login} from "./Login";
import authTheme from "../themes/AuthTheme";

export function Auth() {
    return (
        <ThemeProvider theme={authTheme}>
            <Container fixed>
                <Typography
                    variant="h6"
                    component="span"
                >
                    Вход:
                </Typography>

                <Login />
            </Container>
        </ThemeProvider>
    );
}
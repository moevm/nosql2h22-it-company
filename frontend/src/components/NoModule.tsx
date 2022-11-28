import React from "react";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import {HomeOutlined} from "@mui/icons-material";
import {ThemeProvider} from "@mui/material";
import noModuleTheme from "../themes/NoModuleTheme";

export function NoModule() {
    return (
        <ThemeProvider theme={noModuleTheme} >
            <Container>
                <HomeOutlined sx={{fontSize: 72}} />
                <Typography
                    variant="h3"
                    component="h3"
                >
                    Ни один модуль<br />не выбран
                </Typography>
            </Container>
        </ThemeProvider>
    );
}
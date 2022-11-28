import {createTheme} from "@mui/material";

const noModuleTheme = createTheme({
    components: {
        MuiContainer: {
            styleOverrides: {
                root: {
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'center',
                    alignItems: 'center',
                    height: 'calc(100vh - 80px)',
                    width: '100%'
                }
            }
        },
        MuiTypography: {
            styleOverrides: {
                root: {
                    textAlign: 'center'
                }
            }
        }
    }
});

export default noModuleTheme;
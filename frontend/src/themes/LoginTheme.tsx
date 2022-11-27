import {createTheme} from "@mui/material";

const loginTheme = createTheme({
    palette: {
        primary: {
            main: '#660708'
        },
        secondary: {
            main: '#660708'
        }
    },
    components: {
        MuiContainer: {
            styleOverrides: {
                root: {
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'center',
                    alignItems: 'center',
                    gap: '1rem',
                    width: '80%'
                }
            }
        },
        MuiTextField: {
            styleOverrides: {
                root: {
                    width: '100%',
                    border: 3,
                    borderColor: '#660708',
                    borderRadius: 20,

                }
            }
        },
        MuiButton: {
            styleOverrides: {
                root: {
                    padding: '16.5px 0',
                    margin: '3rem 0 0 0',
                    alignItems: 'center',
                    width: '100%',
                    height: '75px',
                    background: '#EFEFEF',
                    border: '3px solid #660708',
                    borderRadius: '20px',
                    flex: 'none',
                    order: 2,
                    flexGrow: 0
                }
            }
        }
    }
});

export default loginTheme;
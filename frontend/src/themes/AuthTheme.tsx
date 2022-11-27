import {createTheme} from "@mui/material";

const authTheme = createTheme({
    components: {
        MuiContainer: {
            styleOverrides: {
                root: {
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'center',
                    alignItems: 'center',
                    padding: '10px 50px',
                    gap: '5rem',
                    position: 'absolute',
                    width: '38vw',
                    height: '100vh',
                    left: '62vw',
                    top: '0px',
                    background: '#F5F3F4',
                    borderRadius: '50px 0px 0px 50px'
                }
            }
        },
        MuiTypography: {
            styleOverrides: {
                root: {
                    fontFamily: 'Comfortaa',
                    fontStyle: 'normal',
                    fontWeight: '400',
                    fontSize: '48px',
                    width: '70%'
                }
            }
        }
    }
});

export default authTheme;
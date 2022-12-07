import {createTheme} from "@mui/material";

const footerTheme = createTheme({
    components: {
        MuiPaper: {
            styleOverrides: {
                root: {
                    backgroundColor: '#F5F3F4',
                    display: 'flex',
                    flexDirection: 'row',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    height: '80px',
                    width: '100%', 
                    position: 'fixed', 
                    bottom: 0,
                    boxShadow: 'none',
                    borderTop: 'solid #660708'
                }
            }
        },
        MuiButtonGroup: {
            styleOverrides: {
                root: {
                    padding: '0 150px',
                    gap: '25px'
                }
            }
        },
        MuiIconButton: {
            styleOverrides: {
                root: {
                    fontSize: '32px'
                }
            }
        }
    }
});

export default footerTheme;
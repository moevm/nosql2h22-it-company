import {createTheme} from "@mui/material";

const personSearchTheme = createTheme({
    components: {
        MuiContainer: {
            styleOverrides: {
                root: {
                    padding: '30px'
                }
            }
        },
        MuiOutlinedInput: {
            styleOverrides: {
                root: {
                    borderRadius: '20px'
                }
            }
        }
    }
});

export default personSearchTheme;
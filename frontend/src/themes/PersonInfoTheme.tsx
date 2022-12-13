import {createTheme} from "@mui/material";

const personInfoTheme = createTheme({
    components: {
        MuiStack: {
            defaultProps: {
                sx: {
                    margin: '0 150px',
                    padding: '30px',
                    border: '3px solid #660708',
                    borderRadius: '20px',
                    height: 'calc(100vh - 380px)',
                    overflowY: 'scroll'
                }
            }
        }
    }
});

export default personInfoTheme;
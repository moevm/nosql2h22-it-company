import {createTheme} from "@mui/material";

const headerTheme = createTheme({
    palette: {
        primary: {
            main: '#F5F3F4'
        },
        secondary: {
            main: '#660708'
        }
    },
    components: {
        MuiAppBar: {
            styleOverrides: {
                root: {
                    display: 'flex',
                    flexDirection: 'row',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                    position: 'sticky', 
                    top: 0,
                    zIndex: 3,
                    height: '80px',
                    boxShadow: 'none',
                    borderBottom: 'solid #660708'
                }
            }
        },
        MuiButton: {
            styleOverrides: {
                root: {
                    marginRight: '50px',
                    textTransform: 'none',
                    fontSize: '24px',
                    color: 'black',
                    gap: '1rem'
                }
            }
        },
        MuiMenu: {
            styleOverrides: {
                list: {
                    marginLeft: '0'
                }
            }
        },
        MuiList: {
            styleOverrides: {
                root: {
                    marginLeft: '50px',
                    display: 'flex',
                    flexDirection: 'row',
                    justifyContent: 'center',
                    alignItems: 'center',
                    gap: '1.5rem'
                }
            }
        },
        MuiListItem: {
            styleOverrides: {
                root: {
                    height: 60
                }
            }
        },
        MuiListItemText: {
            styleOverrides: {
                primary: {
                    fontSize: '24px'
                }
            }
        },
        MuiListItemIcon: {
            styleOverrides: {
                root: {
                    fontSize: '32px'
                }
            }
        }
    }
});

export default headerTheme;
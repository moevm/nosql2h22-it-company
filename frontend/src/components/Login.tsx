import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import Alert from "@mui/material/Alert";
import Button from "@mui/material/Button";
import Container from "@mui/material/Container";
import FormControl from "@mui/material/FormControl";
import IconButton from "@mui/material/IconButton";
import InputAdornment from "@mui/material/InputAdornment";
import InputLabel from "@mui/material/InputLabel";
import OutlinedInput from "@mui/material/OutlinedInput";
import TextField from "@mui/material/TextField";
import {ThemeProvider} from "@mui/material";
import {Visibility, VisibilityOff} from "@mui/icons-material";
import loginTheme from "../themes/LoginTheme";
import {ACCESS_TOKEN, REFRESH_TOKEN} from "../constants";
import {useActions} from "../hooks/useAction";

interface IData {
    login: string,
    password: string,
    showPassword: boolean,
    showAlert: boolean
}

export function Login() {
    const [data, setData] = useState<IData>({
        login: "",
        password: "",
        showPassword: false,
        showAlert: false
    });

    const {changeUser} = useActions();

    const navigate = useNavigate();

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setData({
            ...data,
            showAlert: false,
        });
        axios.post<IUser>(`${process.env.REACT_APP_AUTH_HOST}${process.env.REACT_APP_PUBLIC_API}${process.env.REACT_APP_AUTH_SIGN_IN}`, {
            login: data.login,
            password: data.password
        }).then(response => {
            localStorage.setItem(ACCESS_TOKEN, response.data.accessToken);
            localStorage.setItem(REFRESH_TOKEN, response.data.refreshToken);
            changeUser(response.data.accessToken);
            navigate(`${process.env.REACT_APP_HOME_PAGE}`);
        }).catch(() => {
            setData({
                ...data,
                showAlert: true,
            });
        });
    };

    const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
        setData({
            ...data,
            [event.target.name]: event.target.value
        });
    };

    const handleClickShowPassword = () => {
        setData({
            ...data,
            showPassword: !data.showPassword,
        });
    };

    const handleMouseDownShowPassword = (event: React.MouseEvent) => {
        event.preventDefault();
    };

    return (
        <form onSubmit={handleSubmit} style={{width: '100%'}}>
            <ThemeProvider theme={loginTheme}>
                <Container>
                    <TextField
                        label="Логин"
                        name="login"
                        required
                        InputLabelProps={{required: false}}
                        fullWidth
                        onChange={handleInput}
                    />
                    <FormControl fullWidth>
                        <InputLabel>Пароль</InputLabel>
                        <OutlinedInput
                            label="Пароль"
                            name="password"
                            required
                            type={data.showPassword ? "text" : "password"}
                            onChange={handleInput}
                            endAdornment={
                                <InputAdornment position="end">
                                    <IconButton
                                        onClick={handleClickShowPassword}
                                        onMouseDown={handleMouseDownShowPassword}
                                        edge="end"
                                    >
                                        {data.showPassword ? <VisibilityOff /> : <Visibility />}
                                    </IconButton>
                                </InputAdornment>
                            }
                        />
                    </FormControl>
                    <Alert
                        sx={{"width": "80%"}}
                        severity="error"
                        style={data.showAlert ? {"visibility": "visible"} : {"visibility": "hidden"}}
                    >Неверный логин или пароль</Alert>
                    <Button
                        type="submit"
                        fullWidth
                        sx={{textTransform: 'none', fontSize: '24px'}}
                    >
                        Войти
                    </Button>
                </Container>
            </ThemeProvider>
        </form>
    );
}

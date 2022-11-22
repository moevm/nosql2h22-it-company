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
    const navigate = useNavigate();

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        setData({
            ...data,
            showAlert: false,
        });
        axios.post(`http://localhost:8080/public/api/v1/auth/sign-in`, {
            login: data.login,
            password: data.password
        }).then(response => {
            localStorage.setItem("jwt", response.data.jwt);
            navigate("/home");
        }).catch(error => {
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
                    >
                        Войти
                    </Button>
                </Container>
            </ThemeProvider>
        </form>
    );
}
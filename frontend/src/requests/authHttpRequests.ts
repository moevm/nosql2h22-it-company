import axios, {AxiosResponse} from "axios";
import authorizedAxios from "./authorizedAxios";
import {getSignInUserPath, getSignUpUserPath} from "./pathResolutionService";

export function signUpUser(userRegistration: IUserRegistration): Promise<AxiosResponse<ISignUpResponse>> {
    return authorizedAxios.post<ISignUpResponse>(getSignUpUserPath(), userRegistration)
}

export function signInUser(login: string, password: string): Promise<AxiosResponse<IUser>> {
    return axios.post(getSignInUserPath(), {
        login: login,
        password: password
    })
}



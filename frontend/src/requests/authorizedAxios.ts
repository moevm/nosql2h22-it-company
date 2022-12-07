import axios from 'axios'
import {DEFAULT_AXIOS_TIMEOUT, UNAUTHORIZED_STATUS_CODE} from "../constants/authorizationConstants";
import {getRefreshTokenPath} from "./pathResolutionService";
import {ACCESS_TOKEN, REFRESH_TOKEN} from "../constants";

const authorizedAxios = axios.create({timeout: DEFAULT_AXIOS_TIMEOUT})

authorizedAxios.interceptors.request.use(request => {
    request.headers!!.Authorization = addBearerPrefix(localStorage.accessToken)
    return request
})

export const addBearerPrefix = (token: string): string => {
    return `Bearer ${token}`
}

authorizedAxios.interceptors.response.use(response => response, error => {
    const {response, config} = error

    if (response.status !== UNAUTHORIZED_STATUS_CODE) {
        return Promise.reject(error)
    }

    return axios.get(getRefreshTokenPath(), {
        timeout: DEFAULT_AXIOS_TIMEOUT,
        headers: {
            Authorization: addBearerPrefix(localStorage.refreshToken)
        },
    })
        .then((tokensResponse) => {
            localStorage.setItem(ACCESS_TOKEN, tokensResponse.data.accessToken);
            localStorage.setItem(REFRESH_TOKEN, tokensResponse.data.refreshToken);
            return authorizedAxios(config)
        })
        .catch(() => {
            return Promise.reject(error)
        })
})

export default authorizedAxios

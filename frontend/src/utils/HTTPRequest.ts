import axios from "axios";

export const authRequest = axios.create({
    baseURL: process.env.REACT_APP_AUTH_HOST,
    timeout: 1000,
    headers: {
        Authorization: `Bearer ${localStorage.accessToken}`
    }
});

export const watcherRequest = axios.create({
    baseURL: process.env.REACT_APP_WATCHER_HOST,
    timeout: 1000,
    headers: {
        Authorization: `Bearer ${localStorage.accessToken}`
    }
});

export const personRequest = axios.create({
    baseURL: process.env.REACT_APP_PERSON_HOST,
    timeout: 1000,
    headers: {
        Authorization: `Bearer ${localStorage.accessToken}`
    }
});

export const documentRequest = axios.create({
    baseURL: process.env.REACT_APP_DOCUMENT_HOST,
    timeout: 1000,
    headers: {
        Authorization: `Bearer ${localStorage.accessToken}`
    }
});
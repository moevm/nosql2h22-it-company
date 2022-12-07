import axios from "axios";

export const authRequest = axios.create({
    baseURL: `${process.env.REACT_APP_AUTH_HOST}${process.env.REACT_APP_PUBLIC_API}`,
    timeout: 30000,
    headers: {
        Authorization: `Bearer ${localStorage.accessToken}`
    }
});

export const authAdminRequest = axios.create({
    baseURL: `${process.env.REACT_APP_AUTH_HOST}${process.env.REACT_APP_ADMIN_API}`,
    timeout: 30000,
    headers: {
        Authorization: `Bearer ${localStorage.accessToken}`
    }
});

export const watcherRequest = axios.create({
    baseURL: `${process.env.REACT_APP_WATCHER_HOST}${process.env.REACT_APP_PUBLIC_API}`,
    timeout: 30000,
    headers: {
        Authorization: `Bearer ${localStorage.accessToken}`
    }
});

export const personRequest = axios.create({
    baseURL: `${process.env.REACT_APP_PERSON_HOST}${process.env.REACT_APP_PUBLIC_API}`,
    timeout: 30000,
    headers: {
        Authorization: `Bearer ${localStorage.accessToken}`
    }
});

export const personAdminRequest = axios.create({
    baseURL: `${process.env.REACT_APP_PERSON_HOST}${process.env.REACT_APP_ADMIN_API}`,
    timeout: 30000,
    headers: {
        Authorization: `Bearer ${localStorage.accessToken}`
    }
});

export const documentRequest = axios.create({
    baseURL: `${process.env.REACT_APP_DOCUMENT_HOST}${process.env.REACT_APP_PUBLIC_API}`,
    timeout: 30000,
    headers: {
        Authorization: `Bearer ${localStorage.accessToken}`
    }
});
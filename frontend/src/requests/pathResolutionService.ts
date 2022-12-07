export function getAllProjectsPath(): string {
    return `${process.env.REACT_APP_PERSON_HOST}${process.env.REACT_APP_PUBLIC_API}${process.env.REACT_APP_PROJECT_GET}`
}

export function getRefreshTokenPath(): string {
    return `${process.env.REACT_APP_AUTH_HOST}${process.env.REACT_APP_PUBLIC_API}${process.env.REACT_APP_AUTH_REFRESH_TOKEN}`
}

export function getWatcherSavingPath(): string {
    return `${process.env.REACT_APP_WATCHER_HOST}${process.env.REACT_APP_PUBLIC_API}${process.env.REACT_APP_WATCHER_GET}`
}

export function getAllWatcherRecordsBetweenDatesPath(): string {
    return `${process.env.REACT_APP_WATCHER_HOST}${process.env.REACT_APP_PUBLIC_API}${process.env.REACT_APP_WATCHER_GET_ALL_BETWEEN_DATES}`
}

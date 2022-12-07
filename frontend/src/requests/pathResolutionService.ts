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

export function getPersonInfoPath(): string {
    return `${process.env.REACT_APP_PERSON_HOST}${process.env.REACT_APP_PUBLIC_API}${process.env.REACT_APP_PERSON_GET}`
}

export function getSignUpUserPath(): string {
    return `${process.env.REACT_APP_AUTH_HOST}${process.env.REACT_APP_ADMIN_API}${process.env.REACT_APP_AUTH_SIGN_UP}`
}

export function getSignInUserPath(): string {
    return `${process.env.REACT_APP_AUTH_HOST}${process.env.REACT_APP_PUBLIC_API}${process.env.REACT_APP_AUTH_SIGN_IN}`
}

export function getSignUpPersonPath(): string {
    return `${process.env.REACT_APP_PERSON_HOST}${process.env.REACT_APP_ADMIN_API}${process.env.REACT_APP_PERSON_SIGN_UP}`
}

export function getPersonPath(): string {
    return `${process.env.REACT_APP_PERSON_HOST}${process.env.REACT_APP_PUBLIC_API}${process.env.REACT_APP_PERSON_GET}`
}

export function getPersonWithAdvancedSearchPath(): string {
    return `${process.env.REACT_APP_PERSON_HOST}${process.env.REACT_APP_PUBLIC_API}${process.env.REACT_APP_PERSON_EXTENDED_SEARCH}`
}

export function getPersonByNamePath(): string {
    return `${process.env.REACT_APP_PERSON_HOST}${process.env.REACT_APP_PUBLIC_API}${process.env.REACT_APP_PERSON_SEARCH}`
}

export function getPersonDocumentPath(): string {
    return `${process.env.REACT_APP_DOCUMENT_HOST}${process.env.REACT_APP_ADMIN_API}${process.env.REACT_APP_DOCUMENT_GET_ALL}`
}

export function getSaveNewDocumentPath(): string {
    return `${process.env.REACT_APP_DOCUMENT_HOST}${process.env.REACT_APP_PUBLIC_API}${process.env.REACT_APP_DOCUMENT_GET}`
}

export function getAllUserDocumentsPath(): string {
    return `${process.env.REACT_APP_DOCUMENT_HOST}${process.env.REACT_APP_PUBLIC_API}${process.env.REACT_APP_DOCUMENT_GET_ALL_OWN}`
}

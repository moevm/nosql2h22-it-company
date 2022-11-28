export interface IUser {
    jwt: string
}

export interface IError {
    id: string,
    code: string,
    description?: string,
    timestamp: number
}

export interface IPerson {
    id: string,
    name: string,
    surname: string,
    patronymic: string,
    sex: string,
    birthday: string,
    firstWorkDate: string,
    position: string,
    status: string,
    contacts: {
        phoneNumber: string,
        email: string
    },
    jobTime: {
        start: string,
        end: string
    },
    officeId: string,
    confidential: {
        passportData: {
            number: string,
            issuedPlace: string,
            issuedDate: string
        },
        nationality: string,
        address: string,
        salary: number,
        projectIds: string[]
    },
    comment: string
}
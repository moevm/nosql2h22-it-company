export interface IUser {
    accessToken: string
}

export interface IToken {
    iss: string,
    sub: string,
    iat: number,
    exp: number,
    role: string
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

export interface IDocument {
    id: string,
    type: string,
    user_id: string,
    orderDate: string,
    completeDate: string,
    status: string
}

interface IUser {
    accessToken: string
}

interface IToken {
    iss: string,
    sub: string,
    iat: number,
    exp: number,
    role: string
}

interface IError {
    id: string,
    code: string,
    description?: string,
    timestamp: number
}

interface IPerson {
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

interface IDocument {
    id: string,
    type: string,
    user_id: string,
    orderDate: string,
    status: string
}

interface IUserStore {
    userId: string,
    name: string,
    surname: string,
    advancedRole: boolean
}

type UserStoreState = {
    user: IUserStore | null
};

type UserStoreAction = {
    type: string,
    user: IUserStore | null
};

type DispatchType = (args: UserStoreAction) => UserStoreAction
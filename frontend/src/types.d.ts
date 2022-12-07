interface IUser {
    accessToken: string
    refreshToken: string
}

interface IToken {
    iss: string,
    sub: string,
    iat: number,
    exp: number,
    role: string
}

interface IMessage {
    message: string
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
    completeDate: string,
    status: string
}

interface IUserState {
    id: string,
    name: string,
    surname: string,
    advancedRole: boolean
}


type UserAction = {
    type: string,
    user: IUserStore | null
}

type DispatchType = (args: UserStoreAction) => UserStoreAction

interface IProject {
    id: string;
    name: string;
}

interface ISaveWatcherRecord {
    date: string;
    projectId: string;
    minutesAmount: number;
    comment: string;
}

interface IWatcherRecord {
    id: string,
    date: string,
    projectId: string,
    minutesAmount: number,
    comment: string
}

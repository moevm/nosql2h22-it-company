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

interface IUserRegistration {
    login: string,
    password: string,
    role: string
}

interface ISignUpResponse {
    id: string
}

interface IPersonExtendedRequest {
    name: string | undefined,
    surname: string | undefined,
    patronymic: string | undefined,
    sex: string | undefined,
    position: string | undefined,
    status: string | undefined,
    start_age: number,
    end_age: number
}

interface IPersonDocument {
    person: {
        name: string,
        surname: string,
        contacts: {
            phoneNumber: string,
            email: string
        }
    },
    document: IDocument
}

interface IPersonDocumentRequest {
    sort: string,
    type: string,
    status: string
}

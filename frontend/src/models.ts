export interface IUser {
    jwt: string
}

export interface IError {
    id: string,
    code: string,
    description?: string,
    timestamp: number
}
export const SIGN_IN = "SIGN_IN";
export const SIGN_OUT = "SIGN_OUT";

const initialState: IUserState | null = null;

export const userReducer = (state = initialState, action: UserAction): IUserState | null => {
    switch (action.type) {
        case SIGN_IN:
        case SIGN_OUT:
            return action.user;
        default:
            return state;
    }
}
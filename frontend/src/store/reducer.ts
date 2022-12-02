const USER_SIGN_IN = "USER_SIGN_IN";
const USER_SIGN_OUT = "USER_SIGN_OUT";

export function userSignIn(user: IUserStore) {
    const action: UserStoreAction = {
        type: USER_SIGN_IN,
        user
    };
    return (dispatch: DispatchType) => {
        dispatch(action);
    }
}

export function userSignOut() {
    const action: UserStoreAction = {
        type: USER_SIGN_OUT,
        user: null
    }
    return (dispatch: DispatchType) => {
        dispatch(action);
    }
}

const initialState: UserStoreState = {
    user: null
}

const reducer = (state: UserStoreState = initialState, action: UserStoreAction): UserStoreState => {
    switch (action.type) {
        case USER_SIGN_IN:
        case USER_SIGN_OUT:
            return {
                user: action.user
            }
        default:
            return state;
    }
}

export default reducer;
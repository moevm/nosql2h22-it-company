import {Dispatch} from "react";
import {SIGN_IN, SIGN_OUT} from "../reducers/userReducer";
import {personRequest} from "../../utils/HTTPRequest";
import {ADVANCED_ROLE_LIST} from "../../constants";

export const changeUser = () => {
    return async (dispatch: Dispatch<UserAction>) => {
        await personRequest.get<IPerson>(
            `${process.env.REACT_APP_PERSON_GET}`
        ).then(response => {
            dispatch({
                type: SIGN_IN,
                user: {
                    id: response.data.id,
                    name: response.data.name,
                    surname: response.data.surname,
                    advancedRole: ADVANCED_ROLE_LIST.indexOf(response.data.position) !== -1
                }
            });
        }).catch((error: IError) => {
            dispatch({
                type: SIGN_OUT,
                user: null
            });
        });
    }
}

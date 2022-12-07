import {Dispatch} from "react";
import axios from "axios";
import {SIGN_IN, SIGN_OUT} from "../reducers/userReducer";
import {ADVANCED_ROLE_LIST} from "../../constants";
import {addBearerPrefix} from "../../requests/authorizedAxios";
import {getPersonInfoPath} from "../../requests/pathResolutionService";

export const changeUser = (accessToken: string) => {
    return async (dispatch: Dispatch<UserAction>) => {
        await axios.get<IPerson>(
            getPersonInfoPath(), {
                headers: {
                    Authorization: addBearerPrefix(accessToken)
                }
            }
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
        }).catch(() => {
            dispatch({
                type: SIGN_OUT,
                user: null
            });
        });
    }
}

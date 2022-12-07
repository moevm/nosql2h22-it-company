import {AxiosResponse} from "axios";
import authorizedAxios from "./authorizedAxios";
import {
    getPersonByNamePath,
    getPersonPath,
    getPersonWithAdvancedSearchPath,
    getSignUpPersonPath
} from "./pathResolutionService";

export function signUpPerson(person: IPerson): Promise<AxiosResponse<IMessage>> {
    return authorizedAxios.post(getSignUpPersonPath(), person)
}

export function getPerson(): Promise<AxiosResponse<IPerson>> {
    return authorizedAxios.get(getPersonPath())
}

export function getPersonWithAdvancedSearch(params: IPersonExtendedRequest): Promise<AxiosResponse<IPerson[]>> {
    return authorizedAxios.get(getPersonWithAdvancedSearchPath(), {
        params: params
    })
}

export function getPersonByName(name: string | undefined): Promise<AxiosResponse<IPerson[]>> {
    return authorizedAxios.get(getPersonByNamePath(), {
        params: {
            name: name
        }
    })
}

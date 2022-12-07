import {AxiosResponse} from "axios";
import authorizedAxios from "./authorizedAxios";
import {getAllProjectsPath} from "./pathResolutionService";

export function getAllProjects(size: number = 20): Promise<AxiosResponse<IProject[]>> {
    return authorizedAxios.get<IProject[]>(getAllProjectsPath(), {
        params: {
            size: size
        }
    })
}

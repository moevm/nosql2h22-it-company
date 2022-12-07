import {AxiosResponse} from "axios";
import authorizedAxios from "./authorizedAxios";
import {getAllUserDocumentsPath, getPersonDocumentPath, getSaveNewDocumentPath} from "./pathResolutionService";

export function getPersonDocument(params: IPersonDocumentRequest): Promise<AxiosResponse<IPersonDocument[]>> {
    return authorizedAxios.get(getPersonDocumentPath(), {
        params: params
    })
}

export function saveNewDocument(type: string): Promise<AxiosResponse<IDocument>> {
    return authorizedAxios.post(getSaveNewDocumentPath(), {
        type: type,
    })
}

export function getAllUserDocuments(size: number = 100): Promise<AxiosResponse<IDocument[]>> {
    return authorizedAxios.get(getAllUserDocumentsPath(), {
        params: {
            size: size
        }
    })
}

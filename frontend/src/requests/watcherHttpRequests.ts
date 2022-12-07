import {AxiosResponse} from "axios";
import {getAllWatcherRecordsBetweenDatesPath, getWatcherSavingPath} from "./pathResolutionService";
import authorizedAxios from "./authorizedAxios";

export function getAllWatcherRecordsBetweenDates(from: string, to: string, size: number = 100): Promise<AxiosResponse<IWatcherRecord[]>> {

    return authorizedAxios.get(getAllWatcherRecordsBetweenDatesPath(), {
        params: {
            from: from,
            to: to,
            size: size
        }
    })
}

export function saveWatcherRecord(record: ISaveWatcherRecord): Promise<AxiosResponse<ISaveWatcherRecord>> {
    return authorizedAxios.post(getWatcherSavingPath(), record)
}


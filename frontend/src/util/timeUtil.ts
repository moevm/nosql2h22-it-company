import {FULL_WORK_DAY_HOURS, MINUTES_IN_HOUR} from "../constants/timeConstants";

export interface WorkTime {
    minutes: number
    name: string
}

const getFormattedTimeByMinutes = (minutes: number): string => {
    const fillLeadZeroIfNeeded = (number: number): string => {
        return `${(number < 10 ? '0' : '')}${number}`
    }

    const currentHour = Math.floor(minutes / 60)
    const currentMinute = Math.floor(minutes % 60)

    return fillLeadZeroIfNeeded(currentHour) + ":" + fillLeadZeroIfNeeded(currentMinute)
}

export const getWorkTimeByMinutes = (minutes: number): WorkTime => {
    return {minutes: minutes, name: getFormattedTimeByMinutes(minutes)}
}

export const getWorkTimes = (): WorkTime[] => {
    const maxInclusiveMinutes = FULL_WORK_DAY_HOURS * MINUTES_IN_HOUR
    const minutesPeriod = 30
    const times: WorkTime[] = []

    for (let minutes = 0; minutes <= maxInclusiveMinutes; minutes += minutesPeriod) {
        times.push(getWorkTimeByMinutes(minutes))
    }

    return times
}

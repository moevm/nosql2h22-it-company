import dayjs, {Dayjs} from "dayjs";
import {DATE_FORMAT, DAY_UNIT, WEEK_UNIT, YEAR_UNIT} from "../constants/dateConstants";
import {EMPTY_STRING} from "../constants";

export const getWeekBounds = (weekDay: Dayjs) => {
    const start = weekDay.startOf(WEEK_UNIT);
    const end = weekDay.endOf(WEEK_UNIT);
    return [start, end]
}

export const getInclusiveDatesRange = (start: Dayjs, end: Dayjs) => {
    const dates: Dayjs[] = []

    for (let current = start; current <= end; current = current.add(1, DAY_UNIT)) {
        dates.push(current)
    }

    return dates
}

export const getWeekTitle = (weekDay: Dayjs | null) => {
    if (weekDay === null) {
        return EMPTY_STRING
    }

    const [start, end] = getWeekBounds(weekDay)
    const title = `${start.format(DATE_FORMAT)} - ${end.format(DATE_FORMAT)}`
    console.log(`Title: ${title}`)
    return title
}

export const calculateAge = (date: string | undefined) => {
    if (date === undefined) {
        return EMPTY_STRING;
    }

    return dayjs().diff(date, YEAR_UNIT);
}

import * as React from 'react';
import {useState} from 'react';
import dayjs, {Dayjs} from 'dayjs';
import isBetweenPlugin from 'dayjs/plugin/isBetween';
import {styled} from '@mui/material/styles';
import TextField from '@mui/material/TextField';
import {AdapterDayjs} from '@mui/x-date-pickers/AdapterDayjs';
import {LocalizationProvider} from '@mui/x-date-pickers/LocalizationProvider';
import {PickersDay, PickersDayProps} from '@mui/x-date-pickers/PickersDay';
import {DatePicker} from '@mui/x-date-pickers/DatePicker';
import {DAY_UNIT} from "../constants/dateConstants";
import {getWeekBounds, getWeekTitle} from "../util/dateUtil";

dayjs.extend(isBetweenPlugin);

interface CustomPickerDayProps extends PickersDayProps<Dayjs> {
    dayIsBetween: boolean;
    isFirstDay: boolean;
    isLastDay: boolean;
}

const CustomPickersDay = styled(PickersDay, {
    shouldForwardProp: (prop) =>
        prop !== 'dayIsBetween' && prop !== 'isFirstDay' && prop !== 'isLastDay',
})<CustomPickerDayProps>(({theme, dayIsBetween, isFirstDay, isLastDay}) => ({
    ...(dayIsBetween && {
        borderRadius: 0,
        backgroundColor: theme.palette.primary.main,
        color: theme.palette.common.white,
        '&:hover, &:focus': {
            backgroundColor: theme.palette.primary.dark,
        },
    }),
    ...(isFirstDay && {
        borderTopLeftRadius: '50%',
        borderBottomLeftRadius: '50%',
    }),
    ...(isLastDay && {
        borderTopRightRadius: '50%',
        borderBottomRightRadius: '50%',
    }),
})) as React.ComponentType<CustomPickerDayProps>;

type PrivateCustomDayProps = {
    onChange: (value: Dayjs | null) => void
}

export default function CustomCalendar({onChange}: PrivateCustomDayProps) {
    const [value, setValue] = useState<Dayjs | null>(dayjs());
    const [title, setTitle] = useState<string>(getWeekTitle(dayjs()))

    const renderWeekPickerDay = (
        date: Dayjs,
        selectedDates: Array<Dayjs | null>,
        pickersDayProps: PickersDayProps<Dayjs>,
    ) => {
        if (!value) {
            return <PickersDay {...pickersDayProps} />;
        }

        const [start, end] = getWeekBounds(value)

        const dayIsBetween = date.isBetween(start, end, null, '[]');
        const isFirstDay = date.isSame(start, DAY_UNIT);
        const isLastDay = date.isSame(end, DAY_UNIT);

        return (
            <CustomPickersDay
                {...pickersDayProps}
                disableMargin
                dayIsBetween={dayIsBetween}
                isFirstDay={isFirstDay}
                isLastDay={isLastDay}
            />
        );
    };

    return (
        <LocalizationProvider dateAdapter={AdapterDayjs} style={{marginTop: "100px"}}>
            <DatePicker
                label="Текущая неделя"
                value={value}

                onChange={(newValue) => {
                    setValue(newValue);
                    onChange(newValue);
                    setTitle(getWeekTitle(newValue))
                }}
                renderDay={renderWeekPickerDay}
                renderInput={(params) => <TextField sx={{width: "300px"}} {...params} />}
                inputFormat={title}
            />
        </LocalizationProvider>
    );
}

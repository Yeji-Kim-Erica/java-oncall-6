package oncall.model;

import oncall.exception.InvalidDayOfWeekException;

/**
 * 요일 Enum
 */
public enum DayOfWeekKOR {
    MON("월"),
    TUES("화"),
    WEDS("수"),
    THURS("목"),
    FRI("금"),
    SAT("토"),
    SUN("일");

    private final String dayOfWeek;

    DayOfWeekKOR(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public static DayOfWeekKOR of(String dayOfWeek) {
        for (DayOfWeekKOR day : DayOfWeekKOR.values()) {
            if (dayOfWeek.equals(day.dayOfWeek)) {
                return day;
            }
        }

        throw new InvalidDayOfWeekException();
    }
}

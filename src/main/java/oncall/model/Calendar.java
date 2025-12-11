package oncall.model;

import oncall.exception.InvalidDayOfWeekException;

import java.time.DateTimeException;
import java.time.Month;

/**
 * 설정 날짜(월, 시작 요일) 정보를 저장하는 클래스
 */
public class Calendar {
    private final Month month;
    private final DayOfWeekKOR firstDayOfMonth;

    public Calendar(Month month, DayOfWeekKOR firstDayOfMonth) {
        this.month = month;
        this.firstDayOfMonth = firstDayOfMonth;
    }

    public static Calendar of(int monthNum, String firstDayOfMonth) {
        try {
            Month month = Month.of(monthNum);
            DayOfWeekKOR dayOfWeek = DayOfWeekKOR.of(firstDayOfMonth);
            return new Calendar(month, dayOfWeek);
        } catch (DateTimeException | InvalidDayOfWeekException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

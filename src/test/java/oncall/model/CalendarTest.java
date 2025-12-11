package oncall.model;

import oncall.exception.InvalidCalendarPropertyFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CalendarTest {
    @Nested
    class SuccessTest {
        @DisplayName("올바른 날짜 정보를 입력하면 Calendar 객체를 생성한다")
        @Test
        void should_Return_Calendar() {
            // given
            int month = 12;
            String dayOfWeek = "월";

            // when & then
            assertThat(Calendar.of(month, dayOfWeek)).isNotNull()
                    .isInstanceOf(Calendar.class);
        }
    }

    @Nested
    class ExceptionTest {
        @DisplayName("1~12의 월 정보를 입력하지 않으면 예외가 발생한다")
        @Test
        void should_ThrowException_ForInvalidMonth() {
            // given
            int month = 13;
            String dayOfWeek = "월";

            // when & then
            assertThatThrownBy(() -> Calendar.of(month, dayOfWeek))
                    .isInstanceOf(InvalidCalendarPropertyFormatException.class);
        }

        @DisplayName("월~일의 요일 정보를 입력하지 않으면 예외가 발생한다")
        @Test
        void should_ThrowException_ForInvalidDayOfWeek() {
            // given
            int month = 12;
            String dayOfWeek = "먀";

            // when & then
            assertThatThrownBy(() -> Calendar.of(month, dayOfWeek))
                    .isInstanceOf(InvalidCalendarPropertyFormatException.class);
        }

    }
}

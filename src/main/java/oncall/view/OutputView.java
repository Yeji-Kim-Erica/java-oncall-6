package oncall.view;

/**
 * 프로그램의 모든 출력을 담당하는 클래스
 */
public class OutputView {
    public void printErrorMessage() {
        System.out.println("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
    }

    public void printCalendarPropertyPrompt() {
        System.out.print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
    };

    public void printWeekdayWorkerNamesPrompt() {
        System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    };

    public void printHolidayWorkerNamesPrompt() {
        System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
    };
}

package oncall.util;

import java.util.Arrays;
import java.util.List;

/**
 * 사용자 입력을 변환, 검증하는 유틸리티 클래스
 */
public final class InputParser {
    private InputParser() {}

    public static List<String> parseToStrings(String input) {
        String refinedInput = refineInput(input);
        return Arrays.stream(refinedInput.split(",")).toList();
    }

    public static int parseToInt(String input) {
        String refinedInput = refineInput(input);
        try {
            return Integer.parseInt(refinedInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private static String refineInput(String input) {
        boolean isNullOrBlank = (input == null) || input.isBlank();
        if (isNullOrBlank) {
            throw new IllegalArgumentException();
        }
        return input.trim();
    }
}
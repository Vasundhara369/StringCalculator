package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
    private static final String DEFAULT_DELIMITER = ",|\n"; // Default delimiters: comma or newline

    /**
     * Adds numbers from a string input.
     * <p>
     * Supports comma and newline as default delimiters. Allows custom delimiters
     * defined at the beginning of the string using the format "//[delimiter]\n" or
     * "//[delim1][delim2]...\n". Numbers greater than 1000 are ignored.
     *
     * @param numbers The input string containing numbers.
     * @return The sum of the numbers. Returns 0 for null or empty input.
     * @throws IllegalArgumentException If negative numbers are present.
     */
    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0; // Handle null or empty input
        }

        String delimiter = DEFAULT_DELIMITER;

        if (numbers.startsWith("//")) {
            delimiter = extractCustomDelimiter(numbers);
            if (delimiter == null) {
                return 0; // Handle invalid custom delimiter format
            }
            numbers = numbers.substring(numbers.indexOf('\n') + 1); // Extract numbers after delimiter definition
        }

        return sumNumbers(numbers, delimiter);
    }

    /**
     * Extracts and parses a custom delimiter from the input.
     *
     * @param numbers The input string.
     * @return The parsed delimiter as a regex, or null if invalid format.
     */
    private String extractCustomDelimiter(String numbers) {
        int newlineIndex = numbers.indexOf('\n');
        if (newlineIndex == -1) {
            return null; // No newline, invalid format
        }

        String delimiter = numbers.substring(2, newlineIndex);
        if (delimiter.startsWith("[") && delimiter.endsWith("]")) {
            delimiter = delimiter.substring(1, delimiter.length() - 1);
            return parseMultipleDelimiters(delimiter);
        } else {
            return Pattern.quote(delimiter); // Escape single-char delimiter for regex
        }
    }

    /**
     * Parses multiple custom delimiters within square brackets.
     *
     * @param delimiters The delimiters string (e.g., "*][%").
     * @return A regex matching any of the delimiters.
     */
    private String parseMultipleDelimiters(String delimiters){
        return Arrays.stream(delimiters.split("]\\["))
                .map(Pattern::quote) // Escape each delimiter for regex
                .collect(Collectors.joining("|")); // Combine with OR
    }

    /**
     * Sums numbers in the input string using the given delimiter.
     * Handles numbers greater than 1000 and negative numbers.
     *
     * @param numbers   The input string.
     * @param delimiter The delimiter to use.
     * @return The sum of the valid numbers.
     * @throws IllegalArgumentException If negative numbers are present.
     */
    private int sumNumbers(String numbers, String delimiter) {
        String[] nums = numbers.split(delimiter);
        List<Integer> negativeNumbers = new ArrayList<>();
        int sum = 0;

        for (String numStr : nums) {
            if (numStr != null && !numStr.trim().isEmpty()) { // Handle empty strings
                try {
                    int num = Integer.parseInt(numStr.trim());
                    if (num < 0) {
                        negativeNumbers.add(num);
                    } else if (num <= 1000) {
                        sum += num;
                    }
                } catch (NumberFormatException e) {
                    // Ignore non-numeric input
                }
            }
        }

        if (!negativeNumbers.isEmpty()) {
            String negativeNumbersString = negativeNumbers.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException("Negative numbers not allowed: " + negativeNumbersString);
        }

        return sum;
    }
}

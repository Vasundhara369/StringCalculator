package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n";
        if (numbers.startsWith("//")) {
            int newlineIndex = numbers.indexOf('\n');
            if (newlineIndex != -1) {
                delimiter = numbers.substring(2, newlineIndex);
                numbers = numbers.substring(newlineIndex + 1);

                if (delimiter.startsWith("[") && delimiter.endsWith("]")) {
                    delimiter = delimiter.substring(1, delimiter.length() - 1);
                    delimiter = parseDelimiters(delimiter);
                } else {
                    delimiter = Pattern.quote(delimiter);
                }
            } else {
                return 0;
            }
        }

        String[] nums = numbers.split(delimiter);

        List<Integer> negativeNumbers = new ArrayList<>();
        int sum = 0;
        for (String numStr : nums) {
            if (numStr != null && !numStr.trim().isEmpty()) {
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
            throw new IllegalArgumentException("negative numbers not allowed " + negativeNumbersString);
        }

        return sum;
    }

    private String parseDelimiters(String delimiters){
        return Arrays.stream(delimiters.split("]\\["))
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
    }
}

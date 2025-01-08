package com.example;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n";
        if (numbers.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(numbers);
            if (matcher.find()) {
                delimiter = matcher.group(1);
                numbers = matcher.group(2);
            }
        }
        return Arrays.stream(numbers.split(delimiter)).mapToInt(Integer::parseInt).sum();
    }
}

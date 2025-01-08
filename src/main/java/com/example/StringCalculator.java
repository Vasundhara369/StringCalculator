package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        String[] nums = numbers.split(delimiter);

        List<Integer> negativeNumbers = new ArrayList<>();
        int sum = 0;
        for (String numStr : nums) {
            int num = parseNumber(numStr.trim(), negativeNumbers);
            sum += num;
        }

        if (!negativeNumbers.isEmpty()) {
            String negativeNumbersString = negativeNumbers.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException("negative numbers not allowed " + negativeNumbersString);
        }

        return sum;
    }

    private int parseNumber(String numStr, List<Integer> negativeNumbers) {
        int num = Integer.parseInt(numStr);
        if (num < 0){
            negativeNumbers.add(num);
        }
        return num <= 1000 ? num: 0;
    }
}

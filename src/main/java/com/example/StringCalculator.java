package com.example;

import java.util.Arrays;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        return Arrays.stream(numbers.split(",")).mapToInt(Integer::parseInt).sum();
    }
}

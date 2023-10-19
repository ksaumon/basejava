package com.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] values = {4, 2, 2, 8, 4, 5, 9, 1, 9};
        int result = minValue(values);
        int[] result1 = minValueArray(values);
        System.out.println("Минимально возможное число: " + result);
        List <Integer> integers = Arrays.stream(result1).boxed().collect(Collectors.toList());
        List <Integer> filteredList = oddOrEven(integers, result);
        System.out.println("Результат: " + filteredList);
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (a, b) -> a * 10 + b);
    }

    public static int[] minValueArray(int[] values) {
        return Arrays.stream(values).distinct().sorted().toArray();
    }

    public static List <Integer> oddOrEven(List <Integer> integers, int result) {
        int sumOfDigits = String.valueOf(result).chars().map(Character::getNumericValue).sum();
        return integers.stream().filter(number -> (sumOfDigits % 2 == 0) != (number % 2 == 0))
                .collect(Collectors.toList());
    }
}

package lotto.domain;

import lotto.constant.ErrorMessage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class Lotto {
    private static final int SIZE = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateDuplication(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_SIZE_ERROR_MESSAGE);
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        HashSet<Integer> duplicatedNumbers = new HashSet<>(numbers);
        if (duplicatedNumbers.size() != SIZE) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_DUPLICATION_ERROR_MESSAGE);
        }
    }

    public String getState() {
        return numbers.toString();
    }
}

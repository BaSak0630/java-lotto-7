package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WinningNumber {
    private final List<Integer> winningNumbers;

    public WinningNumber(String winningNumber) {
        List<Integer> list;
        try {
            String[] split = winningNumber.split(",");
            Stream<String> stream = Arrays.stream(split);
            Stream<Integer> integerStream = stream.map(Integer::parseInt);
            list = integerStream.toList();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        winningNumbers = list;
    }

    public List<Integer> getList() {
        return winningNumbers;
    }
}
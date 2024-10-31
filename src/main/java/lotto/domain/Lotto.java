/*
 * 클래스 이름 Lotto
 *
 * 버전 정보 V1
 *
 * 날짜 10월 31일
 *
 * 저작권 주의
 */
package lotto.domain;

import lotto.constant.ErrorMessage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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

    public Rank getRank(WinningLotto winningLotto) {
        boolean isBonus = false;
        int matchingCount = winningLotto.getMatchingCount(numbers);
        if(matchingCount == 5) {
            Bonus bonus = winningLotto.getBonus();
            isBonus = winningLotto.getMatchingBonus(bonus);
        }
        boolean finalBonus = isBonus;

        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getResult(matchingCount, finalBonus))
                .findFirst()
                .orElse(Rank.NON);
    }

    public int getMatchingCount(List<Integer> numbers) {
        int count = 0;
        for (Integer number : this.numbers) {
            if (numbers.contains(number)) {
                count++;
            }
        }
        return count;
    }
    public boolean getMatchingBonus(int number) {
        for (Integer integer : this.numbers) {
            if (integer == number) {
                return true;
            }
        }
        return false;
    }
}

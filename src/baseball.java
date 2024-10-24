import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class baseball {

    private String secretNumber;

    public baseball() {
        this.secretNumber = generateSecretNumber();
    }

    private String generateSecretNumber() {
        Random random = new Random();
        HashSet<Integer> digits = new HashSet<>();
        StringBuilder secretNumber = new StringBuilder();

        while (digits.size() < 3) {
            int digit = random.nextInt(10);
            if (digits.add(digit)) {
                secretNumber.append(digit);
            }
        }
        return secretNumber.toString();
    }
    public void play() {
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        System.out.println("숫자 야구 게임에 오신 것을 환영합니다!");

        while (true) {
            String userGuess = getUserGuess(scanner);
            attempts++;

            int[] score = calculateScore(secretNumber, userGuess);
            int strikes = score[0];
            int balls = score[1];

            System.out.println(strikes + " 스트라이크, " + balls + " 볼");

            if (strikes == 3) {
                System.out.println("축하합니다! " + attempts + " 번의 시도로 정답을 맞혔습니다: " + secretNumber);
                break;
            }
        }

        scanner.close();
    }

    private String getUserGuess(Scanner scanner) {
        while (true) {
            System.out.print("3자리 숫자를 입력하세요 (예: 123): ");
            String guess = scanner.nextLine();
            if (isValidGuess(guess)) {
                return guess;
            }
            System.out.println("유효하지 않은 입력입니다. 다시 시도하세요.");
        }
    }

    private boolean isValidGuess(String guess) {
        return guess.length() == 3 && guess.chars().allMatch(Character::isDigit) &&
                guess.chars().distinct().count() == 3;
    }

    private int[] calculateScore(String secret, String guess) {
        int strikes = 0;
        int balls = 0;

        for (int i = 0; i < 3; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                strikes++;
            } else if (secret.indexOf(guess.charAt(i)) >= 0) {
                balls++;
            }
        }

        return new int[]{strikes, balls};
    }
}


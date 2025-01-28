package bullscows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Completed Stage 3/7  - https://hyperskill.org/projects/53/stages/289/implement
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int codeLength = scanner.nextInt();
        if (codeLength > 10) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.\n");
        } else {
            String secretCode = randomCodeGenerator(codeLength);
            System.out.println("The random secret number is " + secretCode + ".");
        }

        //String secretCode = "9305";
        //System.out.println("The secret code is prepared: ****.");
        //String guesserInput = scanner.nextLine();
        //System.out.println(checkForCowsAndBulls(secretCode, guesserInput) + " The secret code is 9305.");
    }

    // Generates a random Secret Code with no repeating numbers using a shuffled list.
    public static String randomCodeGenerator(int length) {
        List<Integer> randomList = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));

        do {
            Collections.shuffle(randomList);
        } while (randomList.getFirst() == 0); // The secret code shouldn't start with 0

        StringBuilder result = new StringBuilder();
        for (var ch : randomList.subList(0, length)) {
            result.append(ch);
        }

        return result.toString();
    }

    // Checks the number of cows and bulls and returns the string result
    public static String checkForCowsAndBulls(String secretCode, String guesserInput) {
        int cows = 0;
        int bulls = 0;
        int[] secretCodeArray = secretCode
                .chars()
                .map(Character::getNumericValue)
                .toArray();

        int[] guesserInputArray = guesserInput
                .chars()
                .map(Character::getNumericValue)
                .toArray();

        for (int i = 0; i < secretCodeArray.length; i++) {
            for (int j = 0; j < guesserInputArray.length; j++) {
                if (secretCodeArray[i] == guesserInputArray[j]) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder("Grade: ");
        if (bulls != 0) {
            result.append(bulls).append(" bull(s)");
            if (cows == 0) result.append(".");
        }
        if (cows != 0) {
            if (bulls != 0) {
                result.append(" and ").append(cows).append(" cow(s).");
            } else {
                result.append(cows).append(" cow(s).");
            }

        }
        if (bulls == 0 & cows == 0) {
            result.append("None.");
        }
        return result.toString();
    }
}

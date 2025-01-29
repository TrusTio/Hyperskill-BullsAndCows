package bullscows;

import java.util.*;

/* Completed Stage 7/7  - https://hyperskill.org/projects/53/stages/293/implement
Objectives
In this stage, your program should:
Handle incorrect input.
Print an error message that contains the word error.
After that, don't ask for the numbers again, just finish the program.
P.S. - What a terrible objective/way to handle errors...
*/
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String secretCode = "";

        do {
            System.out.println("Input the length of the secret code:");
            int codeLength = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Input the number of possible symbols in the code:");
            int possibleSymbolCount = scanner.nextInt();
            scanner.nextLine();

            if (codeLength > 36 || codeLength<1) {
                System.out.println("Error: Code length should be 1-36 symbols");
                System.exit(0);
            } else if (codeLength > possibleSymbolCount) {
                System.out.println("Error: it's not possible to generate a code with a length of "
                        + codeLength + " with " + possibleSymbolCount + " unique symbols.");
            } else {
                secretCode = randomCodeGenerator(codeLength, possibleSymbolCount);
                System.out.println("Okay, let's start a game!");
            }
        } while (secretCode.isEmpty());


        String guesserInput;
        int turn = 1;
        do {
            System.out.println("Turn " + turn + ":");
            guesserInput = scanner.nextLine();
            System.out.println(checkForCowsAndBulls(secretCode, guesserInput));
            turn++;
        } while (!guesserInput.equals(secretCode));
        System.out.println("Congratulations! You guessed the secret code.");
    }

    /**
     * Generates a random Secret Code given a length and number of possibly symbols.
     *
     * @param length               length of the code.
     * @param possibleSymbolsCount allowed symbols, if <=10 -> only numbers, >10 alphabetical letters. E.g. 11 will be numbers + 'a'
     * @return String code
     */
    public static String randomCodeGenerator(int length, int possibleSymbolsCount) {
        List<Object> characterList = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (char c = 'a'; c <= 'z'; c++) {
            characterList.add(c);
        }
        List<Object> randomList = characterList.subList(0, possibleSymbolsCount);

        StringBuilder stringToPrint = new StringBuilder("The secret is prepared: ");
        stringToPrint.append("*".repeat(length));
        if (possibleSymbolsCount <= 10) {
            stringToPrint.append(" 0-").append(10 - 1).append(".");
        } else {
            stringToPrint.append(" 0-9,").append("a-").append(randomList.getLast()).append(").");
        }

        //shuffling after generating the string, so I can use getLast
        Collections.shuffle(randomList);
        StringBuilder result = new StringBuilder();
        for (var ch : randomList.subList(0, length)) {
            result.append(ch);
        }

        System.out.println(stringToPrint);
        return result.toString();
    }

    /**
     * Checks the number of cows and bulls in a secretCode given the user input
     *
     * @param secretCode   the secretCode to compare the input to and for check cows and bulls
     * @param guesserInput the input compared to the secret code
     * @return String of cows and bulls
     */
    public static String checkForCowsAndBulls(String secretCode, String guesserInput) {
        int cows = 0;
        int bulls = 0;
        int[] secretCodeArray = secretCode.chars().map(Character::getNumericValue).toArray();

        int[] guesserInputArray = guesserInput.chars().map(Character::getNumericValue).toArray();

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

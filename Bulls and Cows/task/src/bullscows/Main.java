package bullscows;

import java.util.Scanner;

// Completed Stage 2/7  - https://hyperskill.org/projects/53/stages/288/implement
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String secretCode = "9305";
        System.out.println("The secret code is prepared: ****.");
        String guesserInput = scanner.nextLine();
        System.out.println(checkForCowsAndBulls(secretCode, guesserInput) + " The secret code is 9305.");
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
            if(cows == 0) result.append(".");
        }
        if (cows != 0) {
            if(bulls !=0) {
                result.append(" and ").append(cows).append(" cow(s).");
            }else{
                result.append(cows).append(" cow(s).");
            }

        }
        if(bulls == 0 & cows == 0){
            result.append("None.");
        }
        return result.toString();
    }
}

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        String inputSecretCodeLength = sc.nextLine();
        int secretCodeLength = 0;
        if (inputSecretCodeLength.matches("\\d+")) {
            secretCodeLength = Integer.parseInt(inputSecretCodeLength);
            if (secretCodeLength < 1) {
                System.out.println("Error.");
                return;
            } else if (secretCodeLength > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                return;
            }
        } else {
            System.out.println("Error: " + inputSecretCodeLength + " isn't a valid number.");
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        String inputNumberOfPossibleSymbols = sc.nextLine();
        int numberOfPossibleSymbols = 0;
        if (inputNumberOfPossibleSymbols.matches("\\d+")) {
            numberOfPossibleSymbols = Integer.parseInt(inputNumberOfPossibleSymbols);
            if (numberOfPossibleSymbols < 1) {
                System.out.println("error.");
                return;
            } else if (numberOfPossibleSymbols < secretCodeLength) {
                System.out.println("Error: it's not possible to generate a code with a length of " + secretCodeLength +  " with " + numberOfPossibleSymbols + " unique symbols.");
                return;
            } else if (numberOfPossibleSymbols > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                return;
            }
        } else {
            System.out.println("Error: " +inputNumberOfPossibleSymbols + " isn't a valid number.");
            return;

        }

        System.out.print("The secret is prepared: ");
        for (int i = 0; i < secretCodeLength; i++) {
            System.out.print("*");
        }
        System.out.print(" (0-9");

        char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        if (numberOfPossibleSymbols > 10) {
            System.out.println(", a-" + letters[numberOfPossibleSymbols - 11] + ").");
        } else {
            System.out.println(").");
        }

        System.out.println("Okay, let's start a game!");

        String secretCode = new String(randomNumberGenerate(secretCodeLength, numberOfPossibleSymbols));

        if (secretCode.equals("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.")) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
            return;
        }

        int turnCounter = 0;

        while(true) {
            turnCounter++;
            int bullCounter = 0;
            int cowCounter = 0;

            char[] secretCodeToArray = secretCode.toCharArray();
            char[] userTurnCode = sc.next().toCharArray();

            if (userTurnCode.length != 0) {
                ArrayList<Character> symbols = new ArrayList<>();
                for (int i = 0; i < userTurnCode.length; i++) {
                    if (userTurnCode[i] == secretCodeToArray[i]) {
                        bullCounter++;
                    } else {
                        symbols.add(userTurnCode[i]);
                    }
                }

                for (Character s : symbols) {
                    if (secretCode.indexOf(s) != -1) {
                        cowCounter++;
                    }
                }
            }

            System.out.println("Turn " + turnCounter + ":");

            if (bullCounter == secretCodeLength) {
                System.out.println("Grade: " + bullCounter + " bull(s).");
                System.out.println("Congratulations! You guessed the secret code.");
                return;
            } else if (bullCounter == 0 && cowCounter == 0) {
                System.out.println("None.");
            } else if (bullCounter != 0 && cowCounter == 0) {
                System.out.println("Grade: " + bullCounter + " bull(s).");
            } else if (bullCounter != 0 && cowCounter != 0) {
                System.out.println("Grade: " + bullCounter + " bull(s) and " + cowCounter + " cow(s).");
            } else {
                System.out.println("Grade: " + cowCounter + " cow(s).");
            }
        }
    }

    public static StringBuilder randomNumberGenerate(int qty, int range) {
        if (qty > 36) {
            return new StringBuilder("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
        }
        char[] allPossibleSymbols = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        ArrayList<Integer> uniqueIndexes = new ArrayList<>();

        Random random = new Random();

        do {
            int uniqueIndex = random.nextInt(range);
            boolean isUnique = true;
            for (int i : uniqueIndexes) {
                if (uniqueIndex == i) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                uniqueIndexes.add(uniqueIndex);
            }
        }
        while (uniqueIndexes.size() != qty);

        StringBuilder result = new StringBuilder();

        for (int i : uniqueIndexes) {
            result.append(allPossibleSymbols[i]);
        }

        return result;
    }
}


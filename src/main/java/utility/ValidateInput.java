package utility;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Utility class containing static methods for scanning and validating user input in a quiz game.
 * The methods handle different types of inputs such as quiz answers, topic selection, restart game decision,
 * and player name. The class ensures user inputs meet specific requirements and formats.
 */
public class ValidateInput {

    private ValidateInput() {
    }

    public static int scanAnswer(Scanner scanner, int size) {
        String input;
        while (true) {
            System.out.print("-> ");
            input = scanner.nextLine().toLowerCase();

            if (isInputValid(input) && isInputInRange(input, size) && hasInputUniqueValues(input)) {
                break;
            }
        }
        return Integer.parseInt(input);
    }

    public static String scanRestartGameAnswer(Scanner scanner) {
        String input;
        while (true) {
            System.out.print("-> ");
            input = scanner.nextLine().toLowerCase();
            if (input.equals("y") || input.equals("n")) {
                break;
            }
            System.out.println("Invalid input, choose only y/n");
        }
        return input;
    }

    public static String scanPlayerName(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.isBlank()) {
                System.out.println("You should choose some epic name");
                System.out.print("Enter name: ");
            } else if (!Character.isAlphabetic(input.charAt(0))) {
                System.out.println("Your name should start with letter");
                System.out.print("Enter name: ");
            } else {
                break;
            }
        }
        return input;
    }

    private static boolean isInputValid(String input) {
        if (input.isBlank() || input.length() > 4) {
            System.out.println("Invalid answer format!");
            System.out.print("Try again: ");
            return false;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Your answer should be only numbers!");
            System.out.print("Try again: ");
            return false;
        }
    }

    private static boolean isInputInRange(String input, int size) {
        String[] splitted = input.split("");
        for (String s : splitted) {
            if (Integer.parseInt(s) < 1 || Integer.parseInt(s) > size) {
                System.out.println("Only numbers between 1 and " + size);
                System.out.print("Try again: ");
                return false;
            }
        }
        return true;
    }

    private static boolean hasInputUniqueValues(String input) {
        String[] splitted = input.split("");
        Set<String> withoutDuplicates = new HashSet<>(List.of(splitted));

        if (withoutDuplicates.size() != splitted.length) {
            System.out.println("Each answer can be entered only once");
            System.out.print("Try again: ");
            return false;
        }
        return true;
    }
}

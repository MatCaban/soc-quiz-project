package answer;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Answer {


    private Answer(){};

    public static String scanQuizAnswer(Scanner scanner) {
        String input;
        while (true) {
            System.out.print("-> ");
            input = scanner.nextLine().toLowerCase();

            if (isInputValid(input) && isInputInRange(input)) {
                break;
            }
        }

        return input;
    }

    public static String scanTopicAnswer(Scanner scanner) {
        String input;
        while (true) {
            System.out.print("-> ");
            input = scanner.nextLine().toLowerCase();

            if (input.equals("a") || input.equals("b") || input.equals("c")) {
                break;
            }

            System.out.println("Invalid input, choose only from - a, b, c");
        }

        return input;
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

    public static String scanPlayerName(Scanner scanner){
        String input;
        while (true) {
            input = scanner.nextLine();
            if (!input.isBlank()) {
                break;
            }
            System.out.println("You should choose some epic name");
            System.out.print("Enter name: ");
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

    private static boolean isInputInRange(String input) {
        String[]splitted = input.split("");
        Set<String> withoutDuplicates = new HashSet<>(List.of(splitted));

        if (withoutDuplicates.size() != splitted.length) {
            System.out.println("Each answer can be entered only once");
            return false;
        }

        for (String s: splitted) {
            if (Integer.parseInt(s) < 1 || Integer.parseInt(s) > 4) {
                System.out.println("Only numbers between 1 and 4");
                return false;
            }
        }
        return true;
    }
}

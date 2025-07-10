import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Answer {


    private Answer(){};

    public static String scanQuizAnswer(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine();

            if (isInputValid(input) && isInputInRange(input)) {
                break;
            }
        }

        return input;
    }

    public static String scanTopicAnswer(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine().toLowerCase();

            if (input.equals("a") || input.equals("b") || input.equals("c")) {
                break;
            }

            System.out.println("Invalid input, choose only from - a, b, c");
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

import utility.ValidateInput;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizManager quizManager = new QuizManager();

        quizManager.welcomePlayer();
        quizManager.setPlayer();

        while (true) {
            quizManager.printIntro();
            int indexOfTopic = ValidateInput.scanAnswer(scanner, quizManager.getTopicsList().size());
            quizManager.setQuestions(quizManager.getTopicsList().get(indexOfTopic - 1));
            quizManager.setAnswers(quizManager.getTopicsList().get(indexOfTopic - 1));
            quizManager.playQuiz();
            quizManager.printQuizResult();

            System.out.println("Would you like to restart game? y/n");
            String userChoice = ValidateInput.scanRestartGameAnswer(scanner);
            if (!quizManager.shouldPlayAgain(userChoice)) {
                System.out.println("Bye, see you next time!");
                scanner.close();
                break;
            }
        }
    }
}

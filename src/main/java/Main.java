import answer.Answer;
import quiz.QuizManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizManager quizManager = new QuizManager();

        while (true) {
            quizManager.printWelcome();

            String topic = Answer.scanTopicAnswer(scanner);

            switch (topic) {
                case "a" -> quizManager.setQuestionTopics("geography");
                case "b" -> quizManager.setQuestionTopics("java");
                case "c" -> quizManager.setQuestionTopics("science");
            }

            quizManager.playQuiz();
            quizManager.quizResult();

            System.out.println("Would you like to restart game? y/n");
            String shouldContinue = Answer.scanRestartGameAnswer(scanner);
            if (!quizManager.shouldPlayAgain(shouldContinue)) {
                break;
            }
        }
    }
}

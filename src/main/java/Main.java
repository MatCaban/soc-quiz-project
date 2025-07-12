import answer.Answer;
import quiz.QuizManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizManager quizManager = new QuizManager();

        quizManager.welcomePlayer();
        quizManager.setPlayer();

        while (true) {
            quizManager.printIntro();

            String topic = Answer.scanAnswer(scanner, quizManager.getTopicsList().size());
            int indexOfTopic = Integer.parseInt(topic) -1;
            quizManager.setQuestionTopics(quizManager.getTopicsList().get(indexOfTopic));

            quizManager.playQuiz();
            quizManager.quizResult();

            System.out.println("Would you like to restart game? y/n");
            String answer = Answer.scanRestartGameAnswer(scanner);
            if (!quizManager.shouldPlayAgain(answer)) {
                break;
            }
        }
    }
}

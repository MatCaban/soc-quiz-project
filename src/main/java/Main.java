import utility.ValidateInput;
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

            String topic = ValidateInput.scanAnswer(scanner, quizManager.getTopicsList().size());
            int indexOfTopic = Integer.parseInt(topic) -1;
            quizManager.setQuestionTopics(indexOfTopic);

            quizManager.playQuiz();
            quizManager.printQuizResult();

            System.out.println("Would you like to restart game? y/n");
            String answer = ValidateInput.scanRestartGameAnswer(scanner);
            if (!quizManager.shouldPlayAgain(answer)) {
                System.out.println("Bye, see you next time!");
                break;
            }
        }
    }
}

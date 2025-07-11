import answer.Answer;
import quiz.QuizManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizManager quizManager = new QuizManager();

        quizManager.printWelcome();

        String topic = Answer.scanTopicAnswer(scanner);

        switch (topic) {
            case "a" -> quizManager.setQuestionTopics("geography");
            case "b" -> quizManager.setQuestionTopics("java");
            case "c" -> quizManager.setQuestionTopics("science");
        }

        quizManager.playQuiz();
        quizManager.quizResult();


    }
}

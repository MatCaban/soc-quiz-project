import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuizManager {
    private int playerScore;
    private QuestionManager questionManager;
    private Scanner scanner;
   // private List<Question> questions;


    public QuizManager(){
        this.playerScore = 0;
        this.questionManager = new QuestionManager();
        this.scanner = new Scanner(System.in);
       // this.questions = new ArrayList<>();
    }

    //TODO trieda bude manazovat a kontrolovat spravnost odpovedi
    //TODO bude dalsia trieda ktora bude zbierat a validovat odpovede

    public void printWelcome(){
        System.out.println("""
                =========================================================================
                *                        Hello! Welcome to this quiz!                   *
                *                    You can choose from three areas of interest.       *
                *                Each area has 4 questions, and the questions may       *
                *                    (but don't have to!) have multiple answers.        *
                *                           So be careful and focus!                    *
                *                                                                       *
                * Now it's time to choose the area you want questions from, will it be: *
                *                                a) geography                           *
                *                                b) java                                *
                *                                c) science                             *
                *                     Write your answer and we can begin!               *
                =========================================================================""");
    }

    public void setQuestionTopics(String topic){
        this.questionManager.setQuestionsList(topic);
    }

    public void playQuiz() {
        for (int i = 0; i < 4; i++) {
            this.questionManager.printQuestion(i);
            String answer = Answer.scanQuizAnswer(scanner);
            System.out.println(answer);
        }
    }

    public void listOfUserAnswers(String answer) {
        String[] splitted = answer.split("");
        List<Boolean> userAnswers = new ArrayList<>();

        for (int i = 0; i < )
    }
}

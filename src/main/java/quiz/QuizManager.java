package quiz;

import answer.Answer;
import question.QuestionManager;

import java.util.*;

public class QuizManager {
    private int playerScore;
    private QuestionManager questionManager;
    private Scanner scanner;
   // private List<question.Question> questions;


    public QuizManager(){
        this.playerScore = 0;
        this.questionManager = new QuestionManager();
        this.scanner = new Scanner(System.in);
       // this.questions = new ArrayList<>();
    }


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
            Boolean[] userAnswers = listOfUserAnswers(answer);
            Boolean[] actualAnswers = this.questionManager.answerValues(i);

            if (Arrays.equals(userAnswers,actualAnswers)){
                this.playerScore++;
            }
        }
    }

    public void quizResult(){
        System.out.println("Quiz is finished!");
        System.out.println("You've got " + this.playerScore + " out of " + this.questionManager.getQuestions().size());
        switch (this.playerScore) {
            case 4 -> System.out.println("Excellent, you are a genius or something");
            case 3 -> System.out.println("Very good, you are obviously smart");
            case 2 -> System.out.println("Not great, not terrible");
            case 1 -> System.out.println("I still see great potential for improvement");
            case 0 -> System.out.println("C'mon , did you even try?");
        }
    }

    public Boolean[] listOfUserAnswers(String answer) {
        String[] splitted = answer.split("");
        Boolean[] userAnswers = new Boolean[4];
        Arrays.fill(userAnswers,false);

        for (int i = 0; i < splitted.length; i++) {
            userAnswers[Integer.parseInt(splitted[i]) -1 ] = true;
        }
        return userAnswers;
    }
}

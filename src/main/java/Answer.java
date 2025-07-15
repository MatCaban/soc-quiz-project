import utility.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class Answer {
   private final int correctAnswer;


    public Answer(int correctAnswer){
        this.correctAnswer = correctAnswer;
    }

    public int getCorrectAnswer(){
        return this.correctAnswer;
    }

    public boolean isAnswerCorrect(int userChoice) {
        return this.correctAnswer == userChoice;
    }

    public String getAnswerType(){
        if (this.correctAnswer > 4) {
            return QuestionType.MULTIPLE_ANSWER.getComment();
        }
        return QuestionType.SINGLE_ANSWER.getComment();
    }
}

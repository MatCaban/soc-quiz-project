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
}

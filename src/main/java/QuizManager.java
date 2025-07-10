import java.util.List;

public class QuizManager {
    private int playerScore;
    private List<Question> questions;

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
}

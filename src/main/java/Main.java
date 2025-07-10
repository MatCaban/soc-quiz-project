import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {
        String question = "Which is the only continent with land in all four hemispheres?";

        Question question1 = new Question();
        question1.setQuestion(question);

        question1.addAnswer("Africa", true);
        question1.addAnswer("Europe", false);

        question1.printQuestion();

        File file = new File("Question.java");
        System.out.println(file.getAbsoluteFile());

        Gson gson = new Gson();
        try (Reader reader = new FileReader("src/json/geography.json")){
            Question question2 = gson.fromJson(reader, Question.class);
            question2.printQuestion();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}

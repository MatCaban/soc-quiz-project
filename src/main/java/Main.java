import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

        List<Question> questions = new ArrayList<>();

        Gson gson = new Gson();
        try (Reader reader = new FileReader("src/json/geography.json")){
            Type guestionListType = new TypeToken<List<Question>>(){}.getType();
            questions = gson.fromJson(reader, guestionListType);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        for (Question q: questions){
            q.printQuestion();
        }
    }
}

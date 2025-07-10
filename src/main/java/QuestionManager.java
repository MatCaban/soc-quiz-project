import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class QuestionManager {
    private List<Question> questions;

    public QuestionManager(){
        this.questions = new ArrayList<>();
    }

    public void setQuestions(String topics) {
        Gson gson = new Gson();

        try (Reader reader = new FileReader("src/json/" + topics + ".json")) {
            Type questionListType = new TypeToken<List<Question>>(){}.getType();
            this.questions = gson.fromJson(reader,questionListType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Question> qetQuestions(){
        return this.questions;
    }
}

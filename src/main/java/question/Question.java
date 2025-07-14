package question;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import utility.FileNames;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Represents a question that contains its text, a set of possible answers,
 * and a flag indicating whether it is a multiple-choice question.
 * This class also tracks the correct answers.
 */
public class Question {
    private String question;
    private final Map<String, Boolean> answers;
    private boolean multipleChoice;
    private final List<Boolean> answersRightness;

    public Question(){
        this.answers = new HashMap<>();
        this.answersRightness = new ArrayList<>();
        this.question = "";
        this.multipleChoice = false;
    }

    /*
     * Retrieves a list indicating the correctness of each answer for the question.
     * The list contains boolean values, where each value represents whether
     * a corresponding answer is correct (true) or incorrect (false).
     * The method ensures that all values from the internal map of answers
     * are added to the list of correct/incorrect flags.
     */
    public List<Boolean> getAnswersRightness(){
        this.answersRightness.addAll(this.answers.values());
        return this.answersRightness;
    }


    public Map<String, Boolean> getAnswers() {
        return this.answers;
    }

    public String getQuestion() {
        return this.question;
    }

    public boolean isMultipleChoice(){
        return this.multipleChoice;
    }

    public List<Question> setQuestionsList(String topic) {
        List<Question> questions = new ArrayList<>();
        String separator = File.separator;
        String path = String.join(separator,new String[]{FileNames.SRC.getFileName(), FileNames.JSON.getFileName(), FileNames.TOPICS.getFileName()});
        Gson gson = new Gson();

        try (Reader reader = new FileReader(path + separator + topic + ".json")) {
            Type questionListType = new TypeToken<List<Question>>() {
            }.getType();
            questions = gson.fromJson(reader, questionListType);
        } catch (Exception e) {
            System.out.println("Cannon read from file! " + e.getMessage());
        }
        return questions;
    }
}

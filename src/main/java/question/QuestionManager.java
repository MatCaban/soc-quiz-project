package question;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Manages a list of questions for a quiz, including loading questions
 * from a JSON file, printing questions and their answers, and retrieving
 * answer values for a specific question.
 */
public class QuestionManager {
    private List<Question> questions;


    public QuestionManager() {
        this.questions = new ArrayList<>();
    }

    public void setQuestionsList(String topic) {
        String separator = File.separator;
        String[] dirNames = {"src", "json", "topics"};
        String path = String.join(separator,dirNames);
        Gson gson = new Gson();

        try (Reader reader = new FileReader(path + separator + topic + ".json")) {
            Type questionListType = new TypeToken<List<Question>>() {
            }.getType();
            this.questions = gson.fromJson(reader, questionListType);
            Collections.shuffle(this.questions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Question> getQuestions() {
        return this.questions;
    }


    public void printQuestion(int questionNumber) {
        printPrettyQuestion(this.questions.get(questionNumber));
        printPrettyAnswers(this.questions.get(questionNumber).getAnswers());
    }

    private void printPrettyAnswers(Map<String, Boolean> answers) {
        int index = 1;
        for (String answer : answers.keySet()) {
            System.out.println("\t" + index + ". " + answer);
            index++;
        }
    }

    private void printPrettyQuestion(Question question) {
        System.out.print(question.getQuestion());
        System.out.print(question.isMultipleChoice() ? " (Multiple choice question!)\n" : " (Only one correct answer!)\n");
    }

    public Boolean[] answerValues(int questionNumber) {
        return this.questions.get(questionNumber).getRightAnswers().toArray(new Boolean[0]);
    }
}

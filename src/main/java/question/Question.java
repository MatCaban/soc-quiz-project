package question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}

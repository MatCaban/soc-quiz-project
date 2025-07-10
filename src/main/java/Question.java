import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question {
    private String question;
    private final Map<String, Boolean> answers;
    private boolean multipleChoice;
    private final List<Boolean> rightAnswers;

    public Question(){
        this.answers = new HashMap<>();
        this.rightAnswers = new ArrayList<>();
    }

    public List<Boolean> getRightAnswers(){
        this.rightAnswers.addAll(this.answers.values());
        return this.rightAnswers;
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

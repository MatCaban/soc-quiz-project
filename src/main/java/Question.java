import java.util.HashMap;
import java.util.Map;

public class Question {
    private String question;
    private Map<String, Boolean> answers;

    public Question(){
        this.answers = new HashMap<>();
    }

    public void addAnswer(String answer, boolean validity) {
        this.answers.put(answer, validity);
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void printQuestion(){
        int index = 1;
        System.out.println(this.question);
        for (String key : this.answers.keySet()) {
            System.out.println("\t" + index + ". " + key);
            index++;
        }
    }
}

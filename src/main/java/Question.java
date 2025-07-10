import java.util.HashMap;
import java.util.Map;

public class Question {
    private String question;
    private Map<String, Boolean> answers;
    private boolean multipleChoice;

    public Question(){
        this.answers = new HashMap<>();
    }

    public boolean getMultipleChoice(){
        return this.multipleChoice;
    }

    public void printQuestion(){
        int index = 1;
        System.out.print(this.question + " ");
        System.out.print(multipleChoice ? "(Multiple valid answers)\n": "(Only one valid answer)\n");
        for (String key : this.answers.keySet()) {
            System.out.println("\t" + index + ". " + key);
            index++;
        }
    }
}

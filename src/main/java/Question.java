import java.util.Arrays;
import java.util.List;

public class Question {
    String question;
    List<String> alternatives;

    public Question(String question, List<String> alternatives) {
        this.question = question;
        this.alternatives = alternatives;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.question + "\n");
        for (int i = 0; i < this.alternatives.size(); i++){
            result.append((i + 1) + this.alternatives.get(i) + "\n");
        }
        return result.toString();
    }
}

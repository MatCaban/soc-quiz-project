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
        result.append(this.question).append("\n");
        for (int i = 0; i < this.alternatives.size(); i++){
            result.append((i + 1)).append(this.alternatives.get(i)).append("\n");
        }
        return result.toString();
    }
}

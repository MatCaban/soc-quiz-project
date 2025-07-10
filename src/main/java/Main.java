import java.util.List;

public class Main {
    public static void main(String[] args) {
        QuestionManager qm = new QuestionManager();
        qm.setQuestions("java");

        List<Question> questions = qm.qetQuestions();

        for (Question q: questions) {
            q.printQuestion();
        }

    }
}

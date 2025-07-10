public class Main {
    public static void main(String[] args) {
        String question = "Which is the only continent with land in all four hemispheres?";

        Question question1 = new Question();
        question1.setQuestion(question);

        question1.addAnswer("Africa", true);
        question1.addAnswer("Europe", false);

        question1.printQuestion();
    }
}

package utility;

public enum QuestionType {
    SINGLE_ANSWER("Single answer question"), MULTIPLE_ANSWER("Multiple answer question");

    private final String comment;

    QuestionType(String comment) {
        this.comment = comment;
    }

    public String getComment(){
        return this.comment;
    }
}

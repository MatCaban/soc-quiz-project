package utility;

public enum FileNames {
    SRC("src"), JSON("json"), TOPICS("topics"), PLAYERS("players"), QUESTION("questionsSource");

    private String fileName;

    FileNames(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
}

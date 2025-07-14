package quiz;

import player.Player;
import question.Question;
import utility.ValidateInput;
import utility.FileNames;

import java.io.File;
import java.util.*;


/**
 * The QuizManager class is responsible for managing the overall flow of the quiz,
 * including player interactions, question setup, scorekeeping, and result display.
 */
public class QuizManager {
    private final int NUM_OF_QUESTIONS = 4;
    private int playerScore;
    private final Scanner scanner;
    private final List<String> topicsList;
    private Player player;
    private Question question;
    private List<Question> questions;


    public QuizManager() {
        this.playerScore = 0;
        this.scanner = new Scanner(System.in);
        this.topicsList = new ArrayList<>();
        this.player = new Player();
        this.question = new Question();
        this.questions = new ArrayList<>();

    }


    public List<String> getTopicsList() {
        return  this.topicsList;
    }

    public void welcomePlayer() {
        System.out.println("\t\t* Hello! Welcome to this quiz! *\n");
        this.player.printSavedPlayers();
    }

    public void setPlayer() {
        System.out.print("Enter name: ");
        String playerName = ValidateInput.scanPlayerName(this.scanner);
        this.player = this.player.setPlayer(playerName);
    }

    public void printIntro() {
        setTopicsList();
        System.out.println("=".repeat(56));
        System.out.println("\t\tYou can choose from " + this.topicsList.size() + " areas of interest.");
        System.out.println("\tEach area has " + NUM_OF_QUESTIONS + " questions, and the questions may " +
                "\n\t\t(but don't have to!) have multiple answers. " +
                "\n\t\t\tSo be careful and focus!");
        System.out.println("=".repeat(56));

        printTopics();
    }

    public void setQuestionTopics(int index) {
        this.questions = this.question.setQuestionsList(this.topicsList.get(index));
    }

    public void playQuiz() {
        for (int i = 0; i < this.questions.size(); i++) {
            this.printQuestion(i);
            String answer = ValidateInput.scanAnswer(scanner, this.questions.size());
            Boolean[] userAnswers = listOfUserAnswers(answer);
            Boolean[] actualAnswers = this.answersRightness(i);

            if (Arrays.equals(userAnswers, actualAnswers)) {
                this.playerScore++;
            }
        }
    }

    public void printQuizResult() {
        System.out.println("*".repeat(30));
        System.out.println("\t\tQuiz is finished!\n");
        System.out.println("\tYou've got " + this.playerScore + " out of " + this.questions.size() + " points!\n");
        switch (this.playerScore) {
            case 4 -> System.out.println("Excellent, you are a genius or something");
            case 3 -> System.out.println("Very good, you are obviously smart");
            case 2 -> System.out.println("Not great, not terrible");
            case 1 -> System.out.println("I still see great potential for improvement");
            case 0 -> System.out.println("C'mon , did you even try?");
        }
        System.out.println("*".repeat(30));

        this.player.setPlayerPoints(this.playerScore, this.questions.size());
        this.player.printPoints();
        this.player.savePlayer();
    }

    public Boolean[] listOfUserAnswers(String answer) {
        String[] splitted = answer.split("");
        Boolean[] userAnswers = new Boolean[4];
        Arrays.fill(userAnswers, false);

        for (String s : splitted) {
            userAnswers[Integer.parseInt(s) - 1] = true;
        }
        return userAnswers;
    }

    public boolean shouldPlayAgain(String answer) {
        if (answer.equals("y")) {
            this.playerScore = 0;
            this.topicsList.clear();
            return true;
        }
        return false;
    }


    public void printTopics(){
        for (int i = 0; i < this.topicsList.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + this.topicsList.get(i));
        }
    }

    public void setTopicsList(){
        String separator = File.separator;
        String path = String.join(separator, new String[]{FileNames.SRC.getFileName(), FileNames.JSON.getFileName(), FileNames.TOPICS.getFileName()});

        File folder = new File(path);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                int dotIndex = fileName.indexOf(".");
                fileName = fileName.substring(0, dotIndex);
                this.topicsList.add(fileName);
            }
        } else {
            System.out.println("Something went wrong");
        }
    }

    public void printQuestion(int questionNumber) {
        printPrettyQuestion(this.questions.get(questionNumber));
        printPrettyAnswers(this.questions.get(questionNumber).getAnswers());
    }

    private void printPrettyQuestion(Question question) {
        System.out.print(question.getQuestion());
        System.out.print(question.isMultipleChoice() ? " (Multiple choice question!)\n" : " (Only one correct answer!)\n");
    }

    private void printPrettyAnswers(Map<String, Boolean> answers) {
        int index = 1;
        for (String answer : answers.keySet()) {
            System.out.println("\t" + index + ". " + answer);
            index++;
        }
    }

    public Boolean[] answersRightness(int questionNumber) {
        return this.questions.get(questionNumber).getAnswersRightness().toArray(new Boolean[0]);
    }

}

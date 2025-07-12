package quiz;

import answer.Answer;
import player.PlayerManager;
import question.QuestionManager;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * The QuizManager class is responsible for managing the overall flow of the quiz,
 * including player interactions, question setup, scorekeeping, and result display.
 */
public class QuizManager {
    private final int NUM_OF_QUESTIONS = 4;
    private int playerScore;
    private final QuestionManager questionManager;
    private final Scanner scanner;
    private final PlayerManager playerManager;
    private final List<String> topicsList;


    public QuizManager() {
        this.playerScore = 0;
        this.questionManager = new QuestionManager();
        this.playerManager = new PlayerManager();
        this.scanner = new Scanner(System.in);
        topicsList = new ArrayList<>();

    }

    public void welcomePlayer() {
        System.out.println("\t\t* Hello! Welcome to this quiz! *\n");
        try {
            printSavedPlayers();
        } catch (IOException e) {
            System.out.println("Sorry, something went wrong");
        }
    }

    public void setPlayer() {
        System.out.print("Enter name: ");
        String playerName = Answer.scanPlayerName(this.scanner);
        this.playerManager.setPlayer(playerName);
    }

    public List<String> getTopicsList() {
        return  this.topicsList;
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

    public void setQuestionTopics(String topic) {
        this.questionManager.setQuestionsList(topic);
    }

    public void playQuiz() {
        for (int i = 0; i < this.questionManager.getQuestions().size(); i++) {
            this.questionManager.printQuestion(i);
            String answer = Answer.scanAnswer(scanner, questionManager.getQuestions().size());
            Boolean[] userAnswers = listOfUserAnswers(answer);
            Boolean[] actualAnswers = this.questionManager.answerValues(i);

            if (Arrays.equals(userAnswers, actualAnswers)) {
                this.playerScore++;
            }
        }
    }

    public void quizResult() {
        System.out.println("*".repeat(30));
        System.out.println("\t\tQuiz is finished!\n");
        System.out.println("\tYou've got " + this.playerScore + " out of " + this.questionManager.getQuestions().size() + " points!\n");
        switch (this.playerScore) {
            case 4 -> System.out.println("Excellent, you are a genius or something");
            case 3 -> System.out.println("Very good, you are obviously smart");
            case 2 -> System.out.println("Not great, not terrible");
            case 1 -> System.out.println("I still see great potential for improvement");
            case 0 -> System.out.println("C'mon , did you even try?");
        }
        System.out.println("*".repeat(30));

        this.playerManager.setPlayerPoints(this.playerScore);
        this.playerManager.printPoints();
        this.playerManager.savePlayer();
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

    // get the file separator specific for an operation system
    // construct the file path to directory players
    // if this directory does not exist creates it
    // if exists print its content without a file suffix
    private void printSavedPlayers() throws IOException {
        String separator = File.separator;
        String[] dirNames = {"src", "json", "players"};
        String path = String.join(separator, dirNames);
        File folder = new File(path);

        if (!folder.isDirectory()) {
            folder.mkdir();
        }

        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {

            System.out.println("\t\tThere are no saved players yet!");
            System.out.println("\t\tCongratulation you are first to play!");
        } else {
            System.out.println("* There are already some saved players! *");
            for (File file : files) {
                String fileName = file.getName();
                int dotIndex = fileName.indexOf(".");
                fileName = fileName.substring(0, dotIndex);
                System.out.println("\t\t* " + fileName);
            }
            System.out.println("Enter existing name or choose your own");
        }
    }

    public void printTopics(){
        for (int i = 0; i < this.topicsList.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + this.topicsList.get(i));
        }
    }

    public void setTopicsList(){
        String separator = File.separator;
        String[] dirNames = {"src", "json", "topics"};
        String path = String.join(separator, dirNames);

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

}

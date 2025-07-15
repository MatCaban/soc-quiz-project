import player.Player;
import utility.FileNames;
import utility.ValidateInput;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizManager {
    private int playerScore;
    private final Scanner scanner;
    private final List<String> topicsList;
    private Questions questions;
    private Answers answers;
    private Player player;

    public QuizManager(){
        this.scanner = new Scanner(System.in);
        this.topicsList = new ArrayList<>();
        this.questions = new Questions();
        this.answers = new Answers();
        this.player = new Player();
        this.playerScore = 0;
    }

    public void setTopicsList() {
        String separator = File.separator;
        String path = String.join(separator, new String[]{FileNames.SRC.getFileName(), FileNames.QUESTION.getFileName()});

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

    public List<String> getTopicsList(){
        return this.topicsList;
    }

    public void setQuestions(String topic){
        this.questions.setQuestions(topic);
    }

    public void setAnswers(String topic) {
        this.answers.setAnswers(topic);
    }

    public void welcomePlayer(){
        System.out.println("\t\t* Hello! Welcome to this quiz! *\n");
        this.player.printSavedPlayers();
    }

    public void setPlayer() {
        System.out.print("Enter name: ");
        String playerName = ValidateInput.scanPlayerName(this.scanner);
        this.player = this.player.setPlayer(playerName);
    }

    public void printIntro(){
        setTopicsList();
        System.out.println("=".repeat(56));
        System.out.println("\t\tYou can choose from " + this.topicsList.size() + " areas of interest.");
        System.out.println("\tEach area has 4 questions, and the questions may " +
                "\n\t\t(but don't have to!) have multiple answers. " +
                "\n\t\t\tSo be careful and focus!");
        System.out.println("=".repeat(56));
        printTopics();
    }

    public void printTopics(){
        for (int i = 0; i < this.topicsList.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + this.topicsList.get(i));
        }
    }

    public void playQuiz(){
        for (int i = 0; i < this.questions.getQuestions().size(); i++){
            System.out.print(this.questions.getQuestions().get(i));
            System.out.println(" (" + this.answers.getAnswers().get(i).getAnswerType() + ") ");

            int userInput = ValidateInput.scanAnswer(scanner, this.questions.getQuestions().size());

            if (this.answers.getAnswers().get(i).isAnswerCorrect(userInput)) {
                this.playerScore++;
            }
        }
    }

    public void printQuizResult() {
        System.out.println("*".repeat(30));
        System.out.println("\t\tQuiz is finished!\n");
        System.out.println("\tYou've got " + this.playerScore + " out of " + this.questions.getQuestions().size() + " points!\n");
        switch (this.playerScore) {
            case 4 -> System.out.println("Excellent, you are a genius or something");
            case 3 -> System.out.println("Very good, you are obviously smart");
            case 2 -> System.out.println("Not great, not terrible");
            case 1 -> System.out.println("I still see great potential for improvement");
            case 0 -> System.out.println("C'mon , did you even try?");
        }
        System.out.println("*".repeat(30));

        this.player.setPlayerPoints(this.playerScore, this.questions.getQuestions().size());
        this.player.printPoints();
        this.player.savePlayer();
    }

    public boolean shouldPlayAgain(String answer) {
        if (answer.equals("y")) {
            this.playerScore = 0;
            this.topicsList.clear();
            return true;
        }
        return false;
    }
}

package quiz;

import answer.Answer;
import player.PlayerManager;
import question.QuestionManager;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class QuizManager {
    private int playerScore;
    private QuestionManager questionManager;
    private Scanner scanner;
    private PlayerManager playerManager;


    public QuizManager() {
        this.playerScore = 0;
        this.questionManager = new QuestionManager();
        this.scanner = new Scanner(System.in);
        this.playerManager = new PlayerManager();
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
        String playerName = Answer.scanPlayerName(scanner);
        playerManager.setPlayer(playerName);
    }



    public void printIntro() {
        System.out.println("""
                ========================================================================
                *                   You can choose from three areas of interest.       *
                *               Each area has 4 questions, and the questions may       *
                *                   (but don't have to!) have multiple answers.        *
                *                          So be careful and focus!                    *
                *                                                                      *
                * Now it's time to choose the area you want questions from, will it be *
                *                                a) geography                          *
                *                                b) java                               *
                *                                c) science                            *
                *                     Write your answer and we can begin!              *
                ========================================================================""");
    }

    public void setQuestionTopics(String topic) {
        this.questionManager.setQuestionsList(topic);
    }

    public void playQuiz() {
        for (int i = 0; i < 4; i++) {
            this.questionManager.printQuestion(i);
            String answer = Answer.scanQuizAnswer(scanner);
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

        for (int i = 0; i < splitted.length; i++) {
            userAnswers[Integer.parseInt(splitted[i]) - 1] = true;
        }
        return userAnswers;
    }

    public boolean shouldPlayAgain(String answer) {
        if (answer.equals("y")) {
            this.playerScore = 0;
            return true;
        }
        return false;
    }

    private void printSavedPlayers() throws IOException {
        File folder = new File("src/json/players");

        if (!folder.isDirectory()) {
            throw new IOException("Folder not found!");
        }

        File[] files = folder.listFiles();

        if (files.length == 0) {
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



}

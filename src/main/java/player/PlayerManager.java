package player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utility.FileNames;

import java.io.*;
import java.util.Arrays;

/**
 * The PlayerManager class provides functionality to manage a Player object,
 * including setting the player's information, updating points, displaying
 * points, saving the player's data to a JSON file or retrieving data
 * from JSON file.
 */
public class PlayerManager {
    private Player player;

    public PlayerManager() {
        this.player = new Player();
    }


    // get the file separator specific for an operation system
    // construct the file path to directory players
    // if this directory does not exist creates it
    // if exists print its content without a file suffix
    public void printSavedPlayers() {
        String separator = File.separator;
        String path = String.join(separator, new String[]{FileNames.SRC.getFileName(), FileNames.JSON.getFileName(), FileNames.PLAYERS.getFileName()});
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

    // trying to read a file with the player name,
    // if it is successful it will create
    // a new player object with data from a file
    // if this file does not exist,
    // it will create a new player object with default fields values
    public void setPlayer(String playerName) {
        String separator = File.separator;
        String path = String.join(separator, new String[]{FileNames.SRC.getFileName(), FileNames.JSON.getFileName(), FileNames.PLAYERS.getFileName()});
        Gson gson = new Gson();

        try (Reader reader = new FileReader(path + separator + playerName + ".json")) {
            this.player = gson.fromJson(reader, Player.class);
        } catch (IOException e) {
            this.player.setName(playerName);
        }
    }

    public void setPlayerPoints(int pointsEarned, int numOfQuestions) {
        this.player.setPointsEarned(this.player.getPointsEarned() + pointsEarned);
        this.player.setAllPossiblePoints(this.player.getAllPossiblePoints() + numOfQuestions);
    }

    public void printPoints() {
        System.out.println("Your total points earned: " + this.player.getPointsEarned());
        System.out.println("All points you could earned: " + this.player.getAllPossiblePoints());
    }

    public void savePlayer() {
        String separator = File.separator;
        String path = String.join(separator, new String[]{FileNames.SRC.getFileName(), FileNames.JSON.getFileName(), FileNames.PLAYERS.getFileName()});

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer writer = new FileWriter(path + separator + this.player.getName() + ".json")) {
            writer.write(gson.toJson(this.player));
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}

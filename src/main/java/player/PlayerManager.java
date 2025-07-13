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




    /*
     * Prints a list of all saved player names from the designated directory.
     *
     * This method checks a predefined directory for any existing player JSON files
     * and prints their names to the console. If no files are found, it provides a
     * message indicating that no players have been saved yet. If files are present,
     * it lists the saved player names and prompts the user to either select an
     * existing player or enter a new name.
     */
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


    /*
     * Sets the player for the game session by loading player information from a JSON file.
     * If the specified player file does not exist or an I/O error occurs, a new player is created with the given name.
     *
     * @param playerName the name of the player to load or create
     */
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

    /*
     * Saves the current player object to a JSON file.
     *
     * This method serializes a Player object into a JSON format and writes it
     * to a file. The file is stored in a directory structure defined by the
     * FileNames enum (src/json/players). The name of the file corresponds to
     * the name of the player, with a ".json" file extension.
     *
     * If the destination directory or file does not exist, it will create them.
     * If an I/O error occurs during writing, the stack trace of the exception
     * is printed to the console.
     */
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

    public void setPlayerPoints(int pointsEarned, int numOfQuestions) {
        this.player.setPointsEarned(this.player.getPointsEarned() + pointsEarned);
        this.player.setAllPossiblePoints(this.player.getAllPossiblePoints() + numOfQuestions);
    }

    public void printPoints() {
        System.out.println("Your total points earned: " + this.player.getPointsEarned());
        System.out.println("All points you could earned: " + this.player.getAllPossiblePoints());
    }


}

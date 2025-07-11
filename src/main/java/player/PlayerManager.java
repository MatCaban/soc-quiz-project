package player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class PlayerManager {
    private Player player;
    private Gson gson;

    public PlayerManager() {
        this.player = new Player();
    }


    public void setPlayer(String playerName) {
        Gson gson = new Gson();

        try (Reader reader = new FileReader("src/json/players/" + playerName + ".json")) {
            this.player = gson.fromJson(reader, Player.class);

        } catch (IOException e) {
            this.player.setName(playerName);

        }
    }

    public void setPlayerPoints(int pointsEarned) {
        this.player.setPointsEarned(this.player.getPointsEarned() + pointsEarned);
        this.player.setAllPossiblePoints(this.player.getAllPossiblePoints() + 4);

    }

    public void printPoints() {
        System.out.println("Your total points earned: " + this.player.getPointsEarned());
        System.out.println("All points you could earned: " + this.player.getAllPossiblePoints());
    }

    public void savePlayer() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        String fileName = this.player.getName() + ".json";
        try (Writer writer = new FileWriter("src/json/players/" + fileName)) {
            writer.write(gson.toJson(this.player));
            System.out.println("Json bol uspesne zapisany");
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
}

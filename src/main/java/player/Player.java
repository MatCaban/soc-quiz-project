package player;

public class Player {
    private String name;
    private int pointsEarned;
    private int allPossiblePoints;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public int getAllPossiblePoints() {
        return allPossiblePoints;
    }

    public void setAllPossiblePoints(int allPossiblePoints) {
        this.allPossiblePoints = allPossiblePoints;
    }
}

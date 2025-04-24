package main;

public class RaceResults {
    private final boolean isThereAWinner;
    private final String horseWon;
    private final int horsesFallen;
    private final int[] distanceTravelled;

    public RaceResults(boolean isThereAWinner, String horseWon, int horsesFallen, int[] distanceTravelled) {
        this.isThereAWinner = isThereAWinner;
        this.horseWon = horseWon;
        this.horsesFallen = horsesFallen;
        this.distanceTravelled = distanceTravelled;
    }

    public boolean isThereAWinner() {
        return isThereAWinner;
    }

    public String getHorseWon() {
        return horseWon;
    }

    public int getHorsesFallen() {
        return horsesFallen;
    }

    public int[] getDistanceTravelled() {
        return distanceTravelled;
    }
}

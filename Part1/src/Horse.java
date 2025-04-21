
/**
 * Write a description of class Horse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Horse {
    // === Private Fields ===
    private String name;               // Horse's name
    private char symbol;              // Unicode character representing the horse
    private int distanceTravelled;    // How far the horse has moved
    private boolean hasFallen;        // Whether the horse has fallen
    private double confidence;        // Confidence level (0.0 - 1.0)

    // === Constructor ===
    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.symbol = horseSymbol;
        this.name = horseName;
        setConfidence(horseConfidence);     // Use setter to ensure valid range
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }

    // === Public Methods ===

    // Makes the horse fall
    public void fall() {
        hasFallen = true;
    }

    // Resets the horse to the start of the race
    public void goBackToStart() {
        distanceTravelled = 0;
        hasFallen = false;
    }

    // Moves the horse forward by 1 unit
    public void moveForward() {
        distanceTravelled += 1;
    }

    // Getters
    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getDistanceTravelled() {
        return distanceTravelled;
    }

    public boolean hasFallen() {
        return hasFallen;
    }

    public double getConfidence() {
        return confidence;
    }

    // Setters
    public void setSymbol(char newSymbol) {
        symbol = newSymbol;
    }

    public void setConfidence(double newConfidence) {
        // Clamp confidence to range [0.0, 1.0]
        confidence = Math.min(Math.max(newConfidence, 0.0), 1.0);
    }
}

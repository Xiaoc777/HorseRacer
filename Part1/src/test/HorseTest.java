package test;

import main.Horse;

public class HorseTest {

    public static void main(String[] args) {
        // Create a horse
        Horse h = new Horse('♘', "PIPPI LONGSTOCKING", 0.6);
        System.out.println("=== New Horse Created ===");
        System.out.println("Name: " + h.getName());
        System.out.println("Symbol: " + h.getSymbol());
        System.out.println("Confidence: " + h.getConfidence());
        System.out.println("Distance Travelled: " + h.getDistanceTravelled());
        System.out.println("Has Fallen: " + h.hasFallen());

        // Move forward
        h.moveForward();
        h.moveForward();
        System.out.println("\n=== After Moving Forward Twice ===");
        System.out.println("Distance Travelled: " + h.getDistanceTravelled());

        // Make the horse fall
        h.fall();
        System.out.println("\n=== After Falling ===");
        System.out.println("Has Fallen: " + h.hasFallen());

        // Reset to start
        h.goBackToStart();
        System.out.println("\n=== After Reset to Start ===");
        System.out.println("Distance Travelled: " + h.getDistanceTravelled());
        System.out.println("Has Fallen: " + h.hasFallen());

        // Set new symbol
        h.setSymbol('h');
        System.out.println("\n=== After Changing Symbol ===");
        System.out.println("Symbol: " + h.getSymbol());

        // Set confidence out of bounds (should be clamped)
        h.setConfidence(1.2);
        System.out.println("\n=== After Setting Confidence to 1.2 ===");
        System.out.println("Confidence: " + h.getConfidence());

        h.setConfidence(-0.5);
        System.out.println("=== After Setting Confidence to -0.5 ===");
        System.out.println("Confidence: " + h.getConfidence());

        h.setConfidence(0.75);
        System.out.println("=== After Setting Confidence to 0.75 ===");
        System.out.println("Confidence: " + h.getConfidence());
    }
}

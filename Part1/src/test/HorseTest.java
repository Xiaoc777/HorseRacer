package test;

import main.Horse;

public class HorseTest {

    public static void main(String[] args) {
        System.out.println("Unit Test - Class: Horse\n");
        int totalTest = 0;
        int testCorrect = 0;

        // Create a horse
        char horseSymbol = '♘';
        char horseSymbol2 = 'h';
        String horseName = "PIPPI LONGSTOCKING";
        double horseConfidence = 0.6;

        Horse h = new Horse(horseSymbol, horseName, horseConfidence);

        // Test 1 - Check if horse symbol is correct
        System.out.print("Test " + (totalTest + 1) + ": Is horse object created with symbol set as " + horseSymbol + "? ");
        boolean testCondition1 = h.getSymbol() == horseSymbol;
        System.out.println(testCondition1);
        totalTest++;
        if (testCondition1) testCorrect++;

        // Test 2 - Check if horse symbol is correct
        System.out.print("Test " + (totalTest + 1) + ": Is horse object created with name set as " + horseName + "? ");
        boolean testCondition2 = h.getName() == horseName;
        System.out.println(testCondition2);
        totalTest++;
        if (testCondition2) testCorrect++;

        // Test 3 - Check if horse confidence is correct
        System.out.print("Test " + (totalTest + 1) + ": Is horse object created with confidence set as " + horseConfidence + "? ");
        boolean testCondition3 = h.getConfidence() == horseConfidence;
        System.out.println(testCondition3);
        totalTest++;
        if (testCondition3) testCorrect++;

        // Move forward
        h.moveForward();
        h.moveForward();

        // Test 4 - Check if horse has moved by 2 units
        System.out.print("Test " + (totalTest + 1) + ": Has the horse moved by 2 units? ");
        boolean testCondition4 = h.getDistanceTravelled() == 2;
        System.out.println(testCondition4);
        totalTest++;
        if (testCondition4) testCorrect++;

        // Make the horse fall
        h.fall();

        // Test 5 - Check if horse has fallen
        System.out.print("Test " + (totalTest + 1) + ": Is the horse fallen? ");
        boolean testCondition5 = h.hasFallen();
        System.out.println(testCondition5);
        totalTest++;
        if (testCondition5) testCorrect++;

        // Reset to start
        h.goBackToStart();

        // Test 6 - Check if horse is set back to the starting position
        System.out.print("Test " + (totalTest + 1) + ": Has the horse been set back to the starting position? ");
        boolean testCondition6 = h.getDistanceTravelled() == 0 && !h.hasFallen();
        System.out.println(testCondition6);
        totalTest++;
        if (testCondition6) testCorrect++;

        // Set new symbol
        h.setSymbol('h');

        // Test 7 - Check if the horse symbol is changed to 'h'
        System.out.print("Test " + (totalTest + 1) + ": Is the horse symbol changed to 'h'? ");
        boolean testCondition7 = h.getSymbol() == 'h';
        System.out.println(testCondition7);
        totalTest++;
        if (testCondition7) testCorrect++;

        // Set confidence out of bounds (should be clamped)
        h.setConfidence(1.2);

        // Test 8 - Check what happens if the confidence level is being set to 1.2
        System.out.print("Test " + (totalTest + 1) + ": Is the confidence level decreased to 1.0 if it's being set to 1.2? ");
        boolean testCondition8 = h.getConfidence() == 1.0;
        System.out.println(testCondition8);
        totalTest++;
        if (testCondition8) testCorrect++;

        h.setConfidence(-0.5);

        // Test 9 - Check what happens if the confidence level is being set to -0.5
        System.out.print("Test " + (totalTest + 1) + ": Is the confidence level increased to 0.0 if it's being set to -0.5? ");
        boolean testCondition9 = h.getConfidence() == 0.0;
        System.out.println(testCondition9);
        totalTest++;
        if (testCondition9) testCorrect++;

        h.setConfidence(0.75);

        // Test 10 - Check what happens if the confidence level is being set to 0.75
        System.out.print("Test " + (totalTest + 1) + ": Is the confidence level unchanged if it's being set to 0.75? ");
        boolean testCondition10 = h.getConfidence() == 0.75;
        System.out.println(testCondition10);
        totalTest++;
        if (testCondition10) testCorrect++;

        System.out.println();

        System.out.println("All Tests Completed");
        System.out.println("Test Score: " + testCorrect + "/" + totalTest);
        System.out.println("Test Percentage: " + Math.round((double) testCorrect / totalTest * 100) + "%");
    }
}

package test;

import main.Horse;
import main.Race;
import main.RaceResults;

public class RaceTest {

    public static void main(String[] args) {
        Race race = new Race(20);

        // Test 1 - Check the length of the race track

        Horse horse1 = new Horse('♘', "PIPPI LONGSTOCKING", 0.6);
        race.addHorse(horse1, 1);

        // Test 2 - Check if horse1 is in lane 1.

        Horse horse2 = new Horse('♘', "FLUFFY MCFLUFFY", 0.8);
        race.addHorse(horse2, 2);

        // Test 3 - Check if horse2 is in lane 2.

        Horse horse3 = new Horse('♘', "ROGER REFEDRER", 0.4);
        race.addHorse(horse3, 3);

        // Test 3 - Check if horse3 is in lane 3.

        RaceResults results = race.startRace();

        // Test 4 - Check the race results data.

    }
}

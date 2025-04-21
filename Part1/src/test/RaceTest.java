package test;

import main.Horse;
import main.Race;

public class RaceTest {

    public static void main(String[] args) {
        Race race = new Race(20);
        Horse horse1 = new Horse('♘', "PIPPI LONGSTOCKING", 0.6);
        Horse horse2 = new Horse('♘', "FLUFFY MCFLUFFY", 0.8);
        Horse horse3 = new Horse('♘', "ROGER REFEDRER", 0.4);

        race.addHorse(horse1, 1);
        race.addHorse(horse2, 2);
        race.addHorse(horse3, 3);

        race.startRace();
    }
}

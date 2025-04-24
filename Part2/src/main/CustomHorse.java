package main;

import java.awt.*;

public class CustomHorse extends Horse {
    private String breed;
    private String coatColor;

    public CustomHorse(char horseSymbol, String horseName, double horseConfidence, String breed, String color) {
        super(horseSymbol, horseName, horseConfidence);
        this.breed = breed;
        this.coatColor = color;
    }

    public String getBreed() {
        return breed;
    }

    public String getCoatColor() {
        return coatColor;
    }

    public Color getColorAsAwtColor() {
        switch (coatColor.toLowerCase()) {
            case "brown":
                return new Color(139, 69, 19);
            case "black":
                return Color.BLACK;
            case "white":
                return Color.LIGHT_GRAY;
            default:
                return Color.GRAY;
        }
    }
}

package main;

/* TODO: Tasks to work on:-
       1) Add a moving finishing line that can be adjusted by the user.
       2) Change the textbox for race length into a slider with min 10 and max 40.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class RaceGUI extends JFrame {

    private final JPanel trackPanel;
    private ArrayList<CustomHorse> horses;
    private int trackLength = 30;
    private int laneCount = 3;

    private final String[] breeds = {"Thoroughbred", "Arabian", "Quarter Horse"};
    private final String[] colors = {"Brown", "Black", "White"};

    private final Random random = new Random();

    public RaceGUI() {
        setTitle("Horse Race Simulator");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Control Panel
        JPanel controlPanel = new JPanel();
        JTextField laneInput = new JTextField(String.valueOf(laneCount), 5);
        JTextField lengthInput = new JTextField(String.valueOf(trackLength), 5);
        JButton startButton = new JButton("Start Race");

        controlPanel.add(new JLabel("Lanes:"));
        controlPanel.add(laneInput);
        controlPanel.add(new JLabel("Track Length:"));
        controlPanel.add(lengthInput);
        controlPanel.add(startButton);

        // Track Panel
        trackPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTrack(g);
            }
        };
        trackPanel.setPreferredSize(new Dimension(900, 400));
        trackPanel.setBackground(Color.WHITE);

        // Add panels
        add(controlPanel, BorderLayout.NORTH);
        add(trackPanel, BorderLayout.CENTER);

        // Start Button action
        startButton.addActionListener((ActionEvent _) -> {
            try {
                laneCount = Integer.parseInt(laneInput.getText());
                trackLength = Integer.parseInt(lengthInput.getText());
                setupHorses();
                runRace();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
            }
        });
    }

    private void setupHorses() {
        horses = new ArrayList<>();
        for (int i = 0; i < laneCount; i++) {
            char symbol = (char) ('A' + i);
            // Randomize breed and color individually for each horse
            String breed = breeds[random.nextInt(breeds.length)];
            String color = colors[random.nextInt(colors.length)];
            String name = breed + " #" + (i + 1);
            double confidence = 0.5 + random.nextDouble() * 0.5;
            CustomHorse h = new CustomHorse(symbol, name, confidence, breed, color);
            horses.add(h);
        }
    }

    private void drawTrack(Graphics g) {
        if (horses == null) {
            return;
        }

        int laneHeight = trackPanel.getHeight() / laneCount;

        // Draw lanes
        for (int i = 0; i < laneCount; i++) {
            if (i % 2 == 0) {
                g.setColor(new Color(200, 255, 200)); // light green
            } else {
                g.setColor(new Color(220, 220, 220)); // light grey
            }
            g.fillRect(0, i * laneHeight, trackPanel.getWidth(), laneHeight);
        }

        // Draw horses
        for (int i = 0; i < horses.size(); i++) {
            CustomHorse h = horses.get(i);
            int x = 30 + h.getDistanceTravelled() * 20;
            int y = i * laneHeight + 10;

            BufferedImage horseImg = getHorseImage(h);

            if (horseImg != null) {
                g.drawImage(horseImg, x, y, 80, 80, null);
            } else {
                g.setColor(Color.BLACK);
                g.fillOval(x, y, 30, 30);
            }

            // Display horse name above horse
            g.setColor(Color.BLACK);
            g.drawString(h.getName(), x, y + 90);
        }
    }

    private BufferedImage getHorseImage(CustomHorse h) {
        try {
            String imagePath = h.hasFallen() ? "Part2/img/horse_icon/fallen.png"
                    : "Part2/img/horse_icon/" + h.getBreed().toLowerCase().replace(' ', '_') + "_"
                            + h.getCoatColor().toLowerCase() + ".png";
            return ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            return null;
        }
    }

    private void runRace() {
        new Thread(() -> {
            boolean finished = false;
            while (!finished) {
                boolean allFallen = true;
                for (CustomHorse h : horses) {
                    if (!h.hasFallen() && h.getDistanceTravelled() < trackLength) {
                        if (Math.random() < h.getConfidence()) {
                            h.moveForward();
                        } else if (Math.random() < 0.1) {
                            h.fall();
                        }
                    }
                    if (!h.hasFallen()) {
                        allFallen = false;
                    }
                }

                repaint();
                try {
                    Thread.sleep(150);
                } catch (InterruptedException ignored) {
                }

                for (CustomHorse h : horses) {
                    if (h.getDistanceTravelled() >= trackLength) {
                        JOptionPane.showMessageDialog(this, "🏆 Winner: " + h.getName());
                        finished = true;
                        return;
                    }
                }

                if (allFallen) {
                    JOptionPane.showMessageDialog(this,
                            "💀 All horses have fallen! No winner this time!");
                    finished = true;
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RaceGUI().setVisible(true));
    }
}
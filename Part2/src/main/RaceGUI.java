package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class RaceGUI extends JFrame {

    private JPanel trackPanel;
    private JButton startButton;
    private ArrayList<CustomHorse> horses;
    private int trackLength = 30;
    private int laneCount = 3;

    // Breed and Color options
    private final String[] breeds = {"Thoroughbred", "Arabian", "Quarter Horse"};
    private final String[] colors = {"Brown", "Black", "White"};

    public RaceGUI() {
        setTitle("Horse Race Simulator");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Control Panel
        JPanel controlPanel = new JPanel();
        JTextField laneInput = new JTextField(String.valueOf(laneCount), 5);
        JTextField lengthInput = new JTextField(String.valueOf(trackLength), 5);
        JComboBox<String> breedBox = new JComboBox<>(breeds);
        JComboBox<String> colorBox = new JComboBox<>(colors);
        startButton = new JButton("Start Race");

        controlPanel.add(new JLabel("Lanes:"));
        controlPanel.add(laneInput);
        controlPanel.add(new JLabel("Track Length:"));
        controlPanel.add(lengthInput);
        controlPanel.add(new JLabel("Breed:"));
        controlPanel.add(breedBox);
        controlPanel.add(new JLabel("Color:"));
        controlPanel.add(colorBox);
        controlPanel.add(startButton);

        // Track panel
        trackPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTrack(g);
            }
        };
        trackPanel.setPreferredSize(new Dimension(800, 300));
        trackPanel.setBackground(Color.WHITE);

        // Add components to frame
        add(controlPanel, BorderLayout.NORTH);
        add(trackPanel, BorderLayout.CENTER);

        // Start Button action
        startButton.addActionListener((ActionEvent e) -> {
            try {
                laneCount = Integer.parseInt(laneInput.getText());
                trackLength = Integer.parseInt(lengthInput.getText());
                String selectedBreed = (String) breedBox.getSelectedItem();
                String selectedColor = (String) colorBox.getSelectedItem();
                setupHorses(selectedBreed, selectedColor);
                runRace();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
            }
        });
    }

    private void setupHorses(String breed, String color) {
        horses = new ArrayList<>();
        for (int i = 0; i < laneCount; i++) {
            char symbol = (char) ('A' + i);
            String name = breed + " #" + (i + 1);
            double confidence = 0.5 + new Random().nextDouble() * 0.5;
            CustomHorse h = new CustomHorse(symbol, name, confidence, breed, color);
            horses.add(h);
        }
    }

    private void drawTrack(Graphics g) {
        if (horses == null) return;
        for (int i = 0; i < horses.size(); i++) {
            CustomHorse h = horses.get(i);
            int x = 30 + h.getDistanceTravelled() * 20;
            int y = 60 + i * 60;

            g.setColor(h.getColorAsAwtColor());
            g.drawString(h.getSymbol() + " - " + h.getName(), x, y);
        }
    }

    private void runRace() {
        new Thread(() -> {
            boolean finished = false;
            while (!finished) {
                for (CustomHorse h : horses) {
                    if (!h.hasFallen() && h.getDistanceTravelled() < trackLength) {
                        if (Math.random() < h.getConfidence()) {
                            h.moveForward();
                        } else if (Math.random() < 0.1) {
                            h.fall();
                        }
                    }
                }
                repaint();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {}
                for (CustomHorse h : horses) {
                    if (h.getDistanceTravelled() >= trackLength) {
                        JOptionPane.showMessageDialog(this, "🏆 Winner: " + h.getName());
                        finished = true;
                        break;
                    }
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RaceGUI().setVisible(true));
    }
}

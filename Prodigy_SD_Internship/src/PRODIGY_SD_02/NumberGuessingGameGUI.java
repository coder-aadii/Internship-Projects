package PRODIGY_SD_02;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private int randomNumber;
    private int numberOfAttempts;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setBounds(400, 200, 400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Guess a number between 1 and 100:");
        titleLabel.setBounds(20, 20, 300, 25);
        add(titleLabel);

        guessField = new JTextField();
        guessField.setBounds(20, 60, 150, 25);
        add(guessField);

        JButton guessButton = new JButton("Guess");
        guessButton.setBounds(180, 60, 80, 25);
        add(guessButton);

        feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(20, 100, 300, 25);
        add(feedbackLabel);

        attemptsLabel = new JLabel("Number of attempts: 0");
        attemptsLabel.setBounds(20, 140, 200, 25);
        add(attemptsLabel);

        guessButton.addActionListener(new GuessButtonListener());

        // Generate a random number between 1 and 100
        randomNumber = new Random().nextInt(100) + 1;
        numberOfAttempts = 0;
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int userGuess = Integer.parseInt(guessField.getText());
                numberOfAttempts++;

                if (userGuess < randomNumber) {
                    feedbackLabel.setText("Too low! Try again.");
                } else if (userGuess > randomNumber) {
                    feedbackLabel.setText("Too high! Try again.");
                } else {
                    feedbackLabel.setText("Congratulations! You've guessed the number.");
                }

                attemptsLabel.setText("Number of attempts: " + numberOfAttempts);
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGameGUI game = new NumberGuessingGameGUI();
            game.setVisible(true);
        });
    }
}

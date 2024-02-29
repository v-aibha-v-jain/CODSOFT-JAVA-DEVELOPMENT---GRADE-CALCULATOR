package Grade_task_with_GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Grades_with_GUI extends JFrame implements ActionListener {

    private JLabel numSubjectsLabel;
    private JTextField numSubjectsField;
    private JButton calculateButton;
    private JTextArea resultsTextArea;

    public Grades_with_GUI() {
        super("Student Grades");

        numSubjectsLabel = new JLabel("Number of Subjects:");
        numSubjectsField = new JTextField(5);
        calculateButton = new JButton("Calculate");
        resultsTextArea = new JTextArea(10, 20);

        JPanel panel = new JPanel();
        Color color = panel.getBackground();
        resultsTextArea.setBackground(color);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(numSubjectsLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(numSubjectsField, gbc);

        gbc.gridy++;
        panel.add(calculateButton, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(resultsTextArea, gbc);

        calculateButton.addActionListener(this);

        add(panel);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            try {
                int numSubjects = Integer.parseInt(numSubjectsField.getText());

                if (numSubjects < 1) {
                    throw new NumberFormatException("Number of subjects must be at least 1.");
                }

                int totalMarks = 0;
                for (int i = 1; i <= numSubjects; i++) {
                    String input = JOptionPane.showInputDialog(this, "Enter marks for subject " + i + ":");
                    int marks = Integer.parseInt(input);

                    if (marks < 0 || marks > 100) {
                        throw new NumberFormatException("Invalid marks. Please enter marks between 0 and 100.");
                    }

                    totalMarks += marks;
                }

                double averagePercentage = (double) totalMarks / numSubjects;
                String grade = calculateGrade(averagePercentage);

                resultsTextArea.setText(
                        "Total marks: " + totalMarks + "\n" +
                        "Average percentage: " + averagePercentage + "%\n" +
                        "Grade: " + grade
                );
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        new Grades_with_GUI();
    }
}

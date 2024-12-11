import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentGradeTracker {
    private ArrayList<Integer> grades = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentGradeTracker().createAndShowGUI());
    }

    private void createAndShowGUI() {

        JFrame frame = new JFrame("Student Grade Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int halfWidth = screenSize.width / 2;
        int halfHeight = screenSize.height / 2;
        frame.setSize(halfWidth, halfHeight);
        frame.setLocation(screenSize.width / 4, screenSize.height / 4); // Center the frame on the screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JTextField gradeInput = new JTextField();
        JButton addButton = new JButton("Add Grade");
        JButton calculateButton = new JButton("Calculate Results");

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        panel.add(new JLabel("Enter Grade (0-100):"));
        panel.add(gradeInput);
        panel.add(addButton);
        panel.add(calculateButton);
        panel.add(new JScrollPane(outputArea));

        addButton.addActionListener(e -> {
            try {
                int grade = Integer.parseInt(gradeInput.getText());
                if (grade >= 0 && grade <= 100) {
                    grades.add(grade);
                    outputArea.append("Grade added: " + grade + "\n");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter a grade between 0 and 100.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
            gradeInput.setText("");
        });

        calculateButton.addActionListener(e -> {
            if (grades.isEmpty()) {
                outputArea.append("No grades available to calculate.\n");
            } else {
                int total = 0, highest = grades.get(0), lowest = grades.get(0);

                for (int grade : grades) {
                    total += grade;
                    if (grade > highest)
                        highest = grade;
                    if (grade < lowest)
                        lowest = grade;
                }

                double average = (double) total / grades.size();
                outputArea.append("\nResults:\n");
                outputArea.append("Number of Students: " + grades.size() + "\n");
                outputArea.append("Average Score: " + average + "\n");
                outputArea.append("Highest Score: " + highest + "\n");
                outputArea.append("Lowest Score: " + lowest + "\n");
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}

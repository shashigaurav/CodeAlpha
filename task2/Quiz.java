import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz {
    private double balance = 0.0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Quiz().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Simple Banking Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int halfWidth = screenSize.width / 2;
        int halfHeight = screenSize.height / 2;
        frame.setSize(halfWidth, halfHeight);
        frame.setLocation(screenSize.width / 4, screenSize.height / 4);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel amountLabel = new JLabel("Enter Amount:");
        JTextField amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton exitButton = new JButton("Exit");

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(checkBalanceButton);
        panel.add(exitButton);
        panel.add(new JLabel(""));
        panel.add(scrollPane);

        depositButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0) {
                    balance += amount;
                    outputArea.append("Deposited: ₹" + String.format("%.2f", amount) + "\n");
                } else {
                    JOptionPane.showMessageDialog(frame, "Enter a positive amount!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                amountField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        withdrawButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    outputArea.append("Withdrew: ₹" + String.format("%.2f", amount) + "\n");
                } else if (amount > balance) {
                    JOptionPane.showMessageDialog(frame, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Enter a positive amount!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                amountField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a number.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        checkBalanceButton.addActionListener(e -> {
            outputArea.append("Current Balance: ₹" + String.format("%.2f", balance) + "\n");
        });

        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Thank you for using the banking application!", "Goodbye",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}

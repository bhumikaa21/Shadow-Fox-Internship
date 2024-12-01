
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountHolder;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountHolder, double initialDeposit) {
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account opened with balance: " + initialDeposit);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
        transactionHistory.add("Withdrew: " + amount);
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankAccount account = new BankAccount("John Doe", 1000);

            JFrame frame = new JFrame("Bank Account Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel balanceLabel = new JLabel("Balance: " + account.getBalance());
            balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
            balancePanel.add(balanceLabel);

            JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
            inputPanel.setBorder(BorderFactory.createTitledBorder("Transaction Panel"));

            JLabel amountLabel = new JLabel("Enter Amount:");
            JTextField amountField = new JTextField();

            JButton depositButton = new JButton("Deposit");
            JButton withdrawButton = new JButton("Withdraw");
            JButton historyButton = new JButton("View Transaction History");
            JButton clearButton = new JButton("Clear");

            inputPanel.add(amountLabel);
            inputPanel.add(amountField);
            inputPanel.add(depositButton);
            inputPanel.add(withdrawButton);
            inputPanel.add(historyButton);
            inputPanel.add(clearButton);

            JTextArea historyArea = new JTextArea();
            historyArea.setEditable(false);
            historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            JScrollPane historyScrollPane = new JScrollPane(historyArea);
            historyScrollPane.setBorder(BorderFactory.createTitledBorder("Transaction History"));

            depositButton.addActionListener(e -> {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    account.deposit(amount);
                    balanceLabel.setText("Balance: " + account.getBalance());
                    JOptionPane.showMessageDialog(frame, "Deposited " + amount + " successfully.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            withdrawButton.addActionListener(e -> {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    account.withdraw(amount);
                    balanceLabel.setText("Balance: " + account.getBalance());
                    JOptionPane.showMessageDialog(frame, "Withdrew " + amount + " successfully.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            historyButton.addActionListener(e -> {
                historyArea.setText(String.join("\n", account.getTransactionHistory()));
            });

            clearButton.addActionListener(e -> {
                amountField.setText("");
            });

            panel.add(balancePanel, BorderLayout.NORTH);
            panel.add(inputPanel, BorderLayout.CENTER);
            panel.add(historyScrollPane, BorderLayout.SOUTH);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
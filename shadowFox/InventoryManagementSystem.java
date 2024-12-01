
// InventoryManagementSystem.java
// InventoryManagementSystem.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InventoryManagementSystem {
    private List<InventoryItem> items;

    public InventoryManagementSystem() {
        this.items = new ArrayList<>();
    }

    public void addItem(String name, int quantity, double price) {
        items.add(new InventoryItem(name, quantity, price));
    }

    public void updateItem(String name, int quantity, double price) {
        for (InventoryItem item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.setQuantity(quantity);
                item.setPrice(price);
                return;
            }
        }
        throw new IllegalArgumentException("Item not found.");
    }

    public void deleteItem(String name) {
        items.removeIf(item -> item.getName().equalsIgnoreCase(name));
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryManagementSystem ims = new InventoryManagementSystem();

            JFrame frame = new JFrame("Inventory Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JTextArea inventoryArea = new JTextArea();
            inventoryArea.setEditable(false);
            inventoryArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            JScrollPane scrollPane = new JScrollPane(inventoryArea);

            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(5, 2, 10, 10));
            inputPanel.setBorder(BorderFactory.createTitledBorder("Manage Inventory"));

            JTextField nameField = new JTextField();
            JTextField quantityField = new JTextField();
            JTextField priceField = new JTextField();

            inputPanel.add(new JLabel("Item Name:"));
            inputPanel.add(nameField);
            inputPanel.add(new JLabel("Quantity:"));
            inputPanel.add(quantityField);
            inputPanel.add(new JLabel("Price:"));
            inputPanel.add(priceField);

            JButton addButton = new JButton("Add Item");
            JButton updateButton = new JButton("Update Item");
            JButton deleteButton = new JButton("Delete Item");
            JButton clearButton = new JButton("Clear Fields");

            inputPanel.add(addButton);
            inputPanel.add(updateButton);
            inputPanel.add(deleteButton);
            inputPanel.add(clearButton);

            JPanel summaryPanel = new JPanel();
            summaryPanel.setLayout(new BorderLayout());
            JLabel summaryLabel = new JLabel("Total Items: 0 | Total Value: $0.00");
            summaryPanel.add(summaryLabel, BorderLayout.CENTER);

            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(inputPanel, BorderLayout.SOUTH);
            panel.add(summaryPanel, BorderLayout.NORTH);

            addButton.addActionListener(e -> {
                try {
                    String name = nameField.getText().trim();
                    int quantity = Integer.parseInt(quantityField.getText().trim());
                    double price = Double.parseDouble(priceField.getText().trim());
                    ims.addItem(name, quantity, price);
                    updateInventoryArea(inventoryArea, ims);
                    updateSummary(summaryLabel, ims);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            updateButton.addActionListener(e -> {
                try {
                    String name = nameField.getText().trim();
                    int quantity = Integer.parseInt(quantityField.getText().trim());
                    double price = Double.parseDouble(priceField.getText().trim());
                    ims.updateItem(name, quantity, price);
                    updateInventoryArea(inventoryArea, ims);
                    updateSummary(summaryLabel, ims);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            deleteButton.addActionListener(e -> {
                try {
                    String name = nameField.getText().trim();
                    ims.deleteItem(name);
                    updateInventoryArea(inventoryArea, ims);
                    updateSummary(summaryLabel, ims);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            clearButton.addActionListener(e -> {
                nameField.setText("");
                quantityField.setText("");
                priceField.setText("");
            });

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    private static void updateInventoryArea(JTextArea inventoryArea, InventoryManagementSystem ims) {
        StringBuilder sb = new StringBuilder();
        for (InventoryItem item : ims.getItems()) {
            sb.append(item).append("\n");
        }
        inventoryArea.setText(sb.toString());
    }

    private static void updateSummary(JLabel summaryLabel, InventoryManagementSystem ims) {
        int totalItems = ims.getItems().size();
        double totalValue = ims.getItems().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
        summaryLabel.setText("Total Items: " + totalItems + " | Total Value: $" + String.format("%.2f", totalValue));
    }
}

class InventoryItem {
    private String name;
    private int quantity;
    private double price;

    public InventoryItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Item: %-20s | Quantity: %3d | Price: $%7.2f", name, quantity, price);
    }
}
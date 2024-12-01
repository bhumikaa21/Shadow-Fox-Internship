import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;

    // Constructor
    public Contact(String name, String phoneNumber, String email, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + email + ", Address: " + address;
    }
}

public class ContactManagementSystem {
    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Contact Management System ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void addContact() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        String phoneNumber;
        while (true) {
            System.out.print("Enter phone number (10 digits): ");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.matches("\\d{10}")) {
                break;
            } else {
                System.out.println("Invalid phone number. Please enter exactly 10 digits.");
            }
        }

        String email;
        while (true) {
            System.out.print("Enter email: ");
            email = scanner.nextLine();
            if (email.contains("@")) {
                break;
            } else {
                System.out.println("Invalid email. Email should contain '@'.");
            }
        }

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        contacts.add(new Contact(name, phoneNumber, email, address));
        System.out.println("Contact added successfully.");
    }

    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("\n--- Contact List ---");
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }
        }
    }

    private static void updateContact() {
        viewContacts();
        if (!contacts.isEmpty()) {
            System.out.print("Enter the contact number to update: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (index >= 0 && index < contacts.size()) {
                System.out.print("Enter new name (leave blank to keep current): ");
                String name = scanner.nextLine();

                String phoneNumber;
                while (true) {
                    System.out.print("Enter new phone number (10 digits, leave blank to keep current): ");
                    phoneNumber = scanner.nextLine();
                    if (phoneNumber.isEmpty() || phoneNumber.matches("\\d{10}")) {
                        break;
                    } else {
                        System.out.println("Invalid phone number. Please enter exactly 10 digits.");
                    }
                }

                String email;
                while (true) {
                    System.out.print("Enter new email (leave blank to keep current): ");
                    email = scanner.nextLine();
                    if (email.isEmpty() || email.contains("@")) {
                        break;
                    } else {
                        System.out.println("Invalid email. Email should contain '@'.");
                    }
                }

                System.out.print("Enter new address (leave blank to keep current): ");
                String address = scanner.nextLine();

                Contact contact = contacts.get(index);
                if (!name.isEmpty()) contact.setName(name);
                if (!phoneNumber.isEmpty()) contact.setPhoneNumber(phoneNumber);
                if (!email.isEmpty()) contact.setEmail(email);
                if (!address.isEmpty()) contact.setAddress(address);

                System.out.println("Contact updated successfully.");
            } else {
                System.out.println("Invalid contact number.");
            }
        }
    }

    private static void deleteContact() {
        viewContacts();
        if (!contacts.isEmpty()) {
            System.out.print("Enter the contact number to delete: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (index >= 0 && index < contacts.size()) {
                contacts.remove(index);
                System.out.println("Contact deleted successfully.");
            } else {
                System.out.println("Invalid contact number.");
            }
        }
    }
}

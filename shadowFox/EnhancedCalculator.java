import java.util.Scanner;

public class EnhancedCalculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            printMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> performBasicOperations(sc);
                case 2 -> performScientificOperations(sc);
                case 3 -> convertTemperature(sc);
                case 4 -> performBasicConversions(sc);
                case 5 -> calculateAreas(sc);
                case 6 -> convertCurrency(sc);
                case 0 -> System.out.println("\nThank you for using Enhanced Calculator! Goodbye!\n");
                default -> System.out.println("\nInvalid choice. Please try again.\n");
            }
        } while (choice != 0);

        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n================= ENHANCED CALCULATOR =================");
        System.out.println("1. Basic Operations (Add, Subtract, Multiply, Divide)");
        System.out.println("2. Scientific Operations (Sin, Cos, Tan, Log, Sqrt)");
        System.out.println("3. Temperature Conversion (Celsius <-> Fahrenheit)");
        System.out.println("4. Basic Conversions (Length, Weight, etc.)");
        System.out.println("5. Area of Shapes (Rectangle, Square, Circle)");
        System.out.println("6. Currency Conversion");
        System.out.println("0. Exit");
        System.out.println("=======================================================\n");
    }

    private static void performBasicOperations(Scanner sc) {
        System.out.println("\n--- Basic Operations ---");
        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();
        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        System.out.println("Addition: " + (num1 + num2));
        System.out.println("Subtraction: " + (num1 - num2));
        System.out.println("Multiplication: " + (num1 * num2));
        System.out.println("Division: " + (num2 != 0 ? (num1 / num2) : "Undefined (Division by Zero)"));
    }

    private static void performScientificOperations(Scanner sc) {
        System.out.println("\n--- Scientific Operations ---");
        System.out.print("Enter a number: ");
        double num = sc.nextDouble();

        System.out.println("Sine: " + Math.sin(Math.toRadians(num)));
        System.out.println("Cosine: " + Math.cos(Math.toRadians(num)));
        System.out.println("Tangent: " + Math.tan(Math.toRadians(num)));
        System.out.println("Logarithm: " + Math.log(num));
        System.out.println("Square Root: " + Math.sqrt(num));
    }

    private static void convertTemperature(Scanner sc) {
        System.out.println("\n--- Temperature Conversion ---");
        System.out.print("Enter temperature: ");
        double temp = sc.nextDouble();
        System.out.print("Convert to (1) Celsius or (2) Fahrenheit? ");
        int option = sc.nextInt();

        if (option == 1) {
            System.out.println("Temperature in Celsius: " + ((temp - 32) * 5 / 9));
        } else if (option == 2) {
            System.out.println("Temperature in Fahrenheit: " + ((temp * 9 / 5) + 32));
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void performBasicConversions(Scanner sc) {
        System.out.println("\n--- Basic Conversions ---");
        System.out.println("1. Inches to Centimeters");
        System.out.println("2. Pounds to Kilograms");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();

        if (option == 1) {
            System.out.print("Enter inches: ");
            double inches = sc.nextDouble();
            System.out.println("Centimeters: " + (inches * 2.54));
        } else if (option == 2) {
            System.out.print("Enter pounds: ");
            double pounds = sc.nextDouble();
            System.out.println("Kilograms: " + (pounds * 0.453592));
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void calculateAreas(Scanner sc) {
        System.out.println("\n--- Area of Shapes ---");
        System.out.println("1. Rectangle");
        System.out.println("2. Square");
        System.out.println("3. Circle");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();

        switch (option) {
            case 1 -> {
                System.out.print("Enter length: ");
                double length = sc.nextDouble();
                System.out.print("Enter width: ");
                double width = sc.nextDouble();
                System.out.println("Area of Rectangle: " + (length * width));
            }
            case 2 -> {
                System.out.print("Enter side: ");
                double side = sc.nextDouble();
                System.out.println("Area of Square: " + (side * side));
            }
            case 3 -> {
                System.out.print("Enter radius: ");
                double radius = sc.nextDouble();
                System.out.println("Area of Circle: " + (Math.PI * radius * radius));
            }
            default -> System.out.println("Invalid option.");
        }
    }

    private static void convertCurrency(Scanner sc) {
        System.out.println("\n--- Currency Conversion ---");
        System.out.println("1. USD to INR");
        System.out.println("2. INR to USD");
        System.out.print("Enter your choice: ");
        int option = sc.nextInt();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        if (option == 1) {
            System.out.println("Amount in INR: " + (amount * 82.0)); // Example rate
        } else if (option == 2) {
            System.out.println("Amount in USD: " + (amount / 82.0)); // Example rate
        } else {
            System.out.println("Invalid option.");
        }
    }
} 
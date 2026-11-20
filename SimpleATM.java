import java.util.Scanner; // Needed to read user input from the keyboard

public class SimpleATM {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // --- STEP 1: PIN Login ---
        int correctPin = 1234; // Saved PIN code
        int attempts = 0;
        int maxAttempts = 3;
        boolean loggedIn = false;

        // Keep asking for the PIN until it's correct or 3 tries are used
        while (attempts < maxAttempts && !loggedIn) {
            System.out.print("Enter your PIN: ");
            int enteredPin = input.nextInt();

            if (enteredPin == correctPin) {
                loggedIn = true;
                System.out.println("\nLogin successful! Welcome.");
            } else {
                attempts++;
                System.out.println("Wrong PIN. Attempts left: " + (maxAttempts - attempts));
            }
        }

        // --- STEP 2: Main ATM Menu ---
        if (loggedIn) {
            double balance = 500.0; // Starting balance is $500
            String[] transactions = new String[50]; // Array to save transaction history
            int count = 0; // Counts how many transactions have been saved
            int choice = 0;

            // Show the starting balance right after login
            System.out.println("Your starting balance is: $" + balance);

            // Keep repeating the menu until the user picks 5 to exit
            while (choice != 5) {
                System.out.println("\n========= ATM MENU =========");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. View Transactions");
                System.out.println("5. Exit");
                System.out.println("============================");
                System.out.print("Choose an option (1-5): ");
                choice = input.nextInt();

                System.out.println("\n----------------------------");

                if (choice == 1) {
                    // Option 1: Show current balance
                    System.out.println("CURRENT BALANCE: $" + balance);

                } else if (choice == 2) {
                    // Option 2: Deposit money
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = input.nextDouble();

                    if (depositAmount > 0) {
                        balance = balance + depositAmount;

                        // Save this deposit into the array
                        if (count < transactions.length) {
                            transactions[count] = "Deposited: +$" + depositAmount + " (Balance: $" + balance + ")";
                            count++;
                        }
                        System.out.println("Deposit successful!");
                        System.out.println("New balance: $" + balance);
                    } else {
                        System.out.println("ERROR: Please enter an amount greater than $0.");
                    }

                } else if (choice == 3) {
                    // Option 3: Withdraw money
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = input.nextDouble();

                    if (withdrawAmount <= 0) {
                        System.out.println("ERROR: Please enter an amount greater than $0.");
                    } else if (withdrawAmount > balance) {
                        // Show error if user tries to withdraw more than their current balance (e.g. > $500)
                        System.out.println("ERROR: Insufficient funds! You tried to withdraw $" + withdrawAmount
                                + ", but your balance is only $" + balance + ".");
                    } else {
                        // Enough money, so subtract it and save to the array
                        balance = balance - withdrawAmount;

                        if (count < transactions.length) {
                            transactions[count] = "Withdrew: -$" + withdrawAmount + " (Balance: $" + balance + ")";
                            count++;
                        }
                        System.out.println("Withdrawal successful!");
                        System.out.println("New balance: $" + balance);
                    }

                } else if (choice == 4) {
                    // Option 4: View all saved transactions
                    System.out.println("TRANSACTION HISTORY:");

                    if (count == 0) {
                        System.out.println("No transactions made yet.");
                    } else {
                        // Use a for loop to print each transaction in order
                        for (int i = 0; i < count; i++) {
                            System.out.println((i + 1) + ". " + transactions[i]);
                        }
                    }

                } else if (choice == 5) {
                    // Option 5: Exit the program
                    System.out.println("Thank you for using the ATM. Goodbye!");

                } else {
                    System.out.println("ERROR: Invalid choice. Please enter a number from 1 to 5.");
                }

                System.out.println("----------------------------");
            }

        } else {
            // Runs if all 3 PIN attempts fail
            System.out.println("\nToo many wrong attempts. Your account is locked.");
        }

        input.close();
    }
}

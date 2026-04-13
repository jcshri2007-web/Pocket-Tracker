package mini_project;



import java.util.*;

public class ExpenseTrackerApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Tracker tracker = new Tracker();

        tracker.loadFromFile();

        // ✅ Show menu ONLY ONCE
        System.out.println("\n--- MENU ---");
        System.out.println("1. Set Income");
        System.out.println("2. Add Expense");
        System.out.println("3. Add Investment");
        System.out.println("4. Show Summary");
        System.out.println("5. Save Data");
        System.out.println("6. Exit");
        System.out.println("7. Reset All Data");

        int choice;

        do {
            System.out.print("\nEnter next step number: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter income: ");
                    tracker.setIncome(sc.nextDouble());
                    break;

                case 2:
                    System.out.print("Enter category: ");
                    String cat = sc.nextLine();
                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();
                    tracker.addExpense(cat, amt);
                    break;

                case 3:
                    System.out.print("Enter investment type: ");
                    String type = sc.nextLine();
                    System.out.print("Enter amount: ");
                    double invAmt = sc.nextDouble();
                    tracker.addInvestment(type, invAmt);
                    break;

                case 4:
                    tracker.showSummary();
                    break;

                case 5:
                    tracker.saveToFile();
                    break;

                case 6:
                    tracker.saveToFile();
                    System.out.println("Exiting...");
                    break;
                    
                case 7:
                    tracker.resetData();
                    break;
                


                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}

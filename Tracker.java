package mini_project;

import java.util.*;
import java.io.*;

public class Tracker {
    double income;
    ArrayList<Expense> expenses = new ArrayList<>();
    ArrayList<Investment> investments = new ArrayList<>();

    public void setIncome(double income) {
        this.income = income;
    }

    public void addExpense(String category, double amount) {
        expenses.add(new Expense(category, amount));
    }

    public void addInvestment(String type, double amount) {
        investments.add(new Investment(type, amount));
    }

    public double getTotalExpense() {
        double total = 0;
        for (Expense e : expenses) total += e.amount;
        return total;
    }

    public double getTotalInvestment() {
        double total = 0;
        for (Investment i : investments) total += i.amount;
        return total;
    }

    public double getBalance() {
        return income - (getTotalExpense() + getTotalInvestment());
    }

    public void showSummary() {
        System.out.println("\n----- SUMMARY -----");
        System.out.println("Income: " + income);
        System.out.println("Total Expenses: " + getTotalExpense());
        System.out.println("Total Investments: " + getTotalInvestment());
        System.out.println("Balance Left: " + getBalance());

        double spentPercent = (getTotalExpense() / income) * 100;
        double investPercent = (getTotalInvestment() / income) * 100;

        System.out.println("Expense %: " + String.format("%.2f", spentPercent) + "%");
        System.out.println("Investment %: " + String.format("%.2f", investPercent) + "%");

        showWarnings();
        showGraph();
    }
   
    public void resetData() {
        income = 0;
        expenses.clear();
        investments.clear();

        // delete saved file
        File file = new File("data.txt");
        if (file.exists()) {
            file.delete();
        }

        System.out.println("All data has been reset.");
    }
    


    // ⚠️ Budget Warnings
    public void showWarnings() {
        System.out.println("\n--- WARNINGS ---");

        if (getTotalExpense() > 0.8 * income) {
            System.out.println("⚠️ High spending! Expenses exceed 80% of income.");
        }

        if (getBalance() < 0) {
            System.out.println("⚠️ You are in LOSS!");
        }

        if (getTotalExpense() <= 0.5 * income) {
            System.out.println("✅ Spending under control.");
        }
    }

    // 📊 Console Graph
    public void showGraph() {
        System.out.println("\n--- GRAPH ---");

        Graph.showGraph(getTotalExpense(), getTotalInvestment(), getBalance());
        
    }

    private void printBar(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("#");
        }
        System.out.println();
    }

    // 💾 Save data
    public void saveToFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("data.txt"));
            writer.println(income);

            writer.println(expenses.size());
            for (Expense e : expenses) {
                writer.println(e.category + "," + e.amount);
            }

            writer.println(investments.size());
            for (Investment i : investments) {
                writer.println(i.type + "," + i.amount);
            }

            writer.close();
            System.out.println("Data saved.");
        } catch (Exception e) {
            System.out.println("Error saving.");
        }
    }

    // 📂 Load data
    public void loadFromFile() {
        try {
            File file = new File("data.txt");
            if (!file.exists()) return;

            Scanner sc = new Scanner(file);

            income = sc.nextDouble();

            int expCount = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < expCount; i++) {
                String[] parts = sc.nextLine().split(",");
                expenses.add(new Expense(parts[0], Double.parseDouble(parts[1])));
            }

            int invCount = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < invCount; i++) {
                String[] parts = sc.nextLine().split(",");
                investments.add(new Investment(parts[0], Double.parseDouble(parts[1])));
            }

            sc.close();
            System.out.println("Data loaded.");
        } catch (Exception e) {
            System.out.println("Error loading.");
        }
    }
}


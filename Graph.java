package mini_project;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graph {

    public static void showGraph(double expense, double investment, double balance) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(expense, "Amount", "Expenses");
        dataset.setValue(investment, "Amount", "Investment");
        dataset.setValue(balance, "Amount", "Balance");

        JFreeChart chart = ChartFactory.createBarChart(
                "Expense Tracker Graph",
                "Category",
                "Amount",
                dataset
        );

        ChartFrame frame = new ChartFrame("Graph", chart);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}

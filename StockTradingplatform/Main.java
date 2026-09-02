import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// ---------------------------------------------------------
// Stock Class
// ---------------------------------------------------------
class Stock {

    private String symbol;
    private String companyName;
    private double price;

    public Stock(String symbol, String companyName, double price) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


// ---------------------------------------------------------
// Transaction Class
// ---------------------------------------------------------
class Transaction {

    private String type;
    private String stockSymbol;
    private int quantity;
    private double price;
    private double total;

    public Transaction(String type, String stockSymbol,
                       int quantity, double price) {

        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity * price;
    }

    @Override
    public String toString() {

        return type + " | "
                + stockSymbol + " | Quantity: "
                + quantity + " | Price: $"
                + String.format("%.2f", price)
                + " | Total: $"
                + String.format("%.2f", total);
    }

    public String getType() {
        return type;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal() {
        return total;
    }
}


// ---------------------------------------------------------
// Portfolio Class
// ---------------------------------------------------------
class Portfolio {

    private Map<String, Integer> holdings;
    private double cash;
    private double initialInvestment;

    public Portfolio(double initialInvestment) {

        this.cash = initialInvestment;
        this.initialInvestment = initialInvestment;
        this.holdings = new HashMap<>();
    }

    // Get available cash
    public double getCash() {
        return cash;
    }

    // Buy stock
    public boolean buyStock(Stock stock, int quantity) {

        double totalCost = stock.getPrice() * quantity;

        if (quantity <= 0) {
            return false;
        }

        if (totalCost > cash) {
            return false;
        }

        cash -= totalCost;

        holdings.put(
                stock.getSymbol(),
                holdings.getOrDefault(stock.getSymbol(), 0) + quantity
        );

        return true;
    }

    // Sell stock
    public boolean sellStock(Stock stock, int quantity) {

        if (quantity <= 0) {
            return false;
        }

        int currentQuantity =
                holdings.getOrDefault(stock.getSymbol(), 0);

        if (currentQuantity < quantity) {
            return false;
        }

        double totalValue = stock.getPrice() * quantity;

        cash += totalValue;

        int remaining = currentQuantity - quantity;

        if (remaining == 0) {
            holdings.remove(stock.getSymbol());
        } else {
            holdings.put(stock.getSymbol(), remaining);
        }

        return true;
    }

    // Get quantity of a stock
    public int getQuantity(String symbol) {
        return holdings.getOrDefault(symbol, 0);
    }

    // Calculate total value of stocks
    public double getStockValue(ArrayList<Stock> market) {

        double total = 0;

        for (Stock stock : market) {

            int quantity =
                    holdings.getOrDefault(stock.getSymbol(), 0);

            total += quantity * stock.getPrice();
        }

        return total;
    }

    // Calculate total portfolio value
    public double getTotalValue(ArrayList<Stock> market) {

        return cash + getStockValue(market);
    }

    // Calculate profit/loss
    public double getProfitLoss(ArrayList<Stock> market) {

        return getTotalValue(market) - initialInvestment;
    }

    // Display portfolio
    public void displayPortfolio(ArrayList<Stock> market) {

        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("\n==============================================");
        System.out.println("              MY PORTFOLIO");
        System.out.println("==============================================");

        if (holdings.isEmpty()) {

            System.out.println("No stocks currently held.");

        } else {

            System.out.printf(
                    "%-10s %-15s %-10s %-15s%n",
                    "Symbol",
                    "Company",
                    "Quantity",
                    "Value"
            );

            System.out.println("----------------------------------------------");

            for (Stock stock : market) {

                int quantity =
                        holdings.getOrDefault(stock.getSymbol(), 0);

                if (quantity > 0) {

                    double value =
                            quantity * stock.getPrice();

                    System.out.printf(
                            "%-10s %-15s %-10d $%-14s%n",
                            stock.getSymbol(),
                            stock.getCompanyName(),
                            quantity,
                            df.format(value)
                    );
                }
            }
        }

        System.out.println("----------------------------------------------");

        System.out.println(
                "Available Cash     : $"
                        + df.format(cash)
        );

        System.out.println(
                "Stock Value        : $"
                        + df.format(getStockValue(market))
        );

        System.out.println(
                "Total Portfolio    : $"
                        + df.format(getTotalValue(market))
        );

        double profitLoss = getProfitLoss(market);

        System.out.println(
                "Profit / Loss      : $"
                        + df.format(profitLoss)
        );

        System.out.println("==============================================");
    }
}


// ---------------------------------------------------------
// User Class
// ---------------------------------------------------------
class User {

    private String name;
    private Portfolio portfolio;

    public User(String name, double initialInvestment) {

        this.name = name;
        this.portfolio = new Portfolio(initialInvestment);
    }

    public String getName() {
        return name;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}


// ---------------------------------------------------------
// Main Class
// ---------------------------------------------------------
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static ArrayList<Stock> market =
            new ArrayList<>();

    private static ArrayList<Transaction> transactions =
            new ArrayList<>();

    private static User user;

    public static void main(String[] args) {

        initializeMarket();

        System.out.println("==============================================");
        System.out.println("         STOCK TRADING PLATFORM");
        System.out.println("         CodeAlpha Internship Project");
        System.out.println("==============================================");

        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();

        double investment =
                getDoubleInput(
                        "Enter initial investment amount ($): "
                );

        while (investment <= 0) {

            System.out.println(
                    "Investment must be greater than 0."
            );

            investment =
                    getDoubleInput(
                            "Enter initial investment amount ($): "
                    );
        }

        user = new User(name, investment);

        System.out.println(
                "\nWelcome, " + user.getName() + "!"
        );

        int choice;

        do {

            displayMenu();

            choice =
                    getIntegerInput(
                            "Enter your choice: "
                    );

            switch (choice) {

                case 1:
                    displayMarket();
                    break;

                case 2:
                    buyStock();
                    break;

                case 3:
                    sellStock();
                    break;

                case 4:
                    user.getPortfolio()
                            .displayPortfolio(market);
                    break;

                case 5:
                    displayPerformance();
                    break;

                case 6:
                    displayTransactions();
                    break;

                case 7:
                    saveTransactions();
                    break;

                case 8:
                    System.out.println(
                            "\nThank you for using "
                            + "Stock Trading Platform!"
                    );
                    break;

                default:
                    System.out.println(
                            "\nInvalid choice. "
                            + "Please select 1-8."
                    );
            }

        } while (choice != 8);

        scanner.close();
    }


    // -----------------------------------------------------
    // Initialize Market
    // -----------------------------------------------------
    private static void initializeMarket() {

        market.add(
                new Stock("AAPL", "Apple", 195.50)
        );

        market.add(
                new Stock("GOOGL", "Google", 175.25)
        );

        market.add(
                new Stock("MSFT", "Microsoft", 420.75)
        );

        market.add(
                new Stock("AMZN", "Amazon", 185.40)
        );

        market.add(
                new Stock("TSLA", "Tesla", 245.60)
        );

        market.add(
                new Stock("META", "Meta", 525.30)
        );
    }


    // -----------------------------------------------------
    // Display Menu
    // -----------------------------------------------------
    private static void displayMenu() {

        System.out.println("\n==============================================");
        System.out.println("                    MENU");
        System.out.println("==============================================");

        System.out.println("1. Display Market Data");
        System.out.println("2. Buy Stock");
        System.out.println("3. Sell Stock");
        System.out.println("4. View Portfolio");
        System.out.println("5. Portfolio Performance");
        System.out.println("6. Transaction History");
        System.out.println("7. Save Transaction History");
        System.out.println("8. Exit");

        System.out.println("==============================================");
    }


    // -----------------------------------------------------
    // Display Market
    // -----------------------------------------------------
    private static void displayMarket() {

        DecimalFormat df =
                new DecimalFormat("0.00");

        System.out.println("\n==============================================");
        System.out.println("                MARKET DATA");
        System.out.println("==============================================");

        System.out.printf(
                "%-10s %-15s %-15s%n",
                "Symbol",
                "Company",
                "Price"
        );

        System.out.println("----------------------------------------------");

        for (Stock stock : market) {

            System.out.printf(
                    "%-10s %-15s $%-14s%n",
                    stock.getSymbol(),
                    stock.getCompanyName(),
                    df.format(stock.getPrice())
            );
        }

        System.out.println("==============================================");
    }


    // -----------------------------------------------------
    // Buy Stock
    // -----------------------------------------------------
    private static void buyStock() {

        displayMarket();

        System.out.print(
                "\nEnter stock symbol to buy: "
        );

        String symbol =
                scanner.nextLine().toUpperCase();

        Stock stock =
                findStock(symbol);

        if (stock == null) {

            System.out.println(
                    "Stock not found!"
            );

            return;
        }

        int quantity =
                getIntegerInput(
                        "Enter quantity: "
                );

        if (quantity <= 0) {

            System.out.println(
                    "Quantity must be greater than 0."
            );

            return;
        }

        double totalCost =
                stock.getPrice() * quantity;

        System.out.printf(
                "Total Cost: $%.2f%n",
                totalCost
        );

        boolean success =
                user.getPortfolio()
                        .buyStock(stock, quantity);

        if (success) {

            Transaction transaction =
                    new Transaction(
                            "BUY",
                            stock.getSymbol(),
                            quantity,
                            stock.getPrice()
                    );

            transactions.add(transaction);

            System.out.println(
                    "Stock purchased successfully!"
            );

        } else {

            System.out.println(
                    "Insufficient funds!"
            );
        }
    }


    // -----------------------------------------------------
    // Sell Stock
    // -----------------------------------------------------
    private static void sellStock() {

        displayMarket();

        System.out.print(
                "\nEnter stock symbol to sell: "
        );

        String symbol =
                scanner.nextLine().toUpperCase();

        Stock stock =
                findStock(symbol);

        if (stock == null) {

            System.out.println(
                    "Stock not found!"
            );

            return;
        }

        int owned =
                user.getPortfolio()
                        .getQuantity(symbol);

        if (owned == 0) {

            System.out.println(
                    "You do not own this stock."
            );

            return;
        }

        System.out.println(
                "You currently own: "
                        + owned
                        + " shares"
        );

        int quantity =
                getIntegerInput(
                        "Enter quantity to sell: "
                );

        if (quantity <= 0) {

            System.out.println(
                    "Quantity must be greater than 0."
            );

            return;
        }

        if (quantity > owned) {

            System.out.println(
                    "You cannot sell more shares "
                    + "than you own."
            );

            return;
        }

        boolean success =
                user.getPortfolio()
                        .sellStock(stock, quantity);

        if (success) {

            Transaction transaction =
                    new Transaction(
                            "SELL",
                            stock.getSymbol(),
                            quantity,
                            stock.getPrice()
                    );

            transactions.add(transaction);

            System.out.println(
                    "Stock sold successfully!"
            );

        } else {

            System.out.println(
                    "Unable to complete sale."
            );
        }
    }


    // -----------------------------------------------------
    // Portfolio Performance
    // -----------------------------------------------------
    private static void displayPerformance() {

        DecimalFormat df =
                new DecimalFormat("0.00");

        Portfolio portfolio =
                user.getPortfolio();

        double currentValue =
                portfolio.getTotalValue(market);

        double profitLoss =
                portfolio.getProfitLoss(market);

        double initialValue =
                currentValue - profitLoss;

        double percentage =
                (profitLoss / initialValue) * 100;

        System.out.println("\n==============================================");
        System.out.println("           PORTFOLIO PERFORMANCE");
        System.out.println("==============================================");

        System.out.println(
                "Initial Investment : $"
                        + df.format(initialValue)
        );

        System.out.println(
                "Current Value      : $"
                        + df.format(currentValue)
        );

        System.out.println(
                "Profit / Loss      : $"
                        + df.format(profitLoss)
        );

        System.out.println(
                "Return Percentage  : "
                        + df.format(percentage)
                        + "%"
        );

        if (profitLoss > 0) {

            System.out.println(
                    "Performance Status : PROFIT"
            );

        } else if (profitLoss < 0) {

            System.out.println(
                    "Performance Status : LOSS"
            );

        } else {

            System.out.println(
                    "Performance Status : NO CHANGE"
            );
        }

        System.out.println("==============================================");
    }


    // -----------------------------------------------------
    // Transaction History
    // -----------------------------------------------------
    private static void displayTransactions() {

        System.out.println("\n==============================================");
        System.out.println("             TRANSACTION HISTORY");
        System.out.println("==============================================");

        if (transactions.isEmpty()) {

            System.out.println(
                    "No transactions available."
            );

        } else {

            for (int i = 0;
                 i < transactions.size();
                 i++) {

                System.out.println(
                        (i + 1)
                        + ". "
                        + transactions.get(i)
                );
            }
        }

        System.out.println("==============================================");
    }


    // -----------------------------------------------------
    // Save Transactions to File
    // -----------------------------------------------------
    private static void saveTransactions() {

        try {

            FileWriter writer =
                    new FileWriter(
                            "transactions.txt"
                    );

            writer.write(
                    "STOCK TRADING PLATFORM\n"
            );

            writer.write(
                    "User: "
                            + user.getName()
                            + "\n\n"
            );

            for (Transaction transaction :
                    transactions) {

                writer.write(
                        transaction.toString()
                                + "\n"
                );
            }

            writer.close();

            System.out.println(
                    "\nTransaction history saved to "
                            + "transactions.txt"
            );

        } catch (IOException e) {

            System.out.println(
                    "Error saving transactions: "
                            + e.getMessage()
            );
        }
    }


    // -----------------------------------------------------
    // Find Stock
    // -----------------------------------------------------
    private static Stock findStock(String symbol) {

        for (Stock stock : market) {

            if (stock.getSymbol()
                    .equalsIgnoreCase(symbol)) {

                return stock;
            }
        }

        return null;
    }


    // -----------------------------------------------------
    // Integer Input Validation
    // -----------------------------------------------------
    private static int getIntegerInput(
            String message) {

        while (true) {

            System.out.print(message);

            try {

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }


    // -----------------------------------------------------
    // Double Input Validation
    // -----------------------------------------------------
    private static double getDoubleInput(
            String message) {

        while (true) {

            System.out.print(message);

            try {

                return Double.parseDouble(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid amount."
                );
            }
        }
    }
}
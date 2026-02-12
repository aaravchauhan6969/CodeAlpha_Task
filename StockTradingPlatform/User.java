package model;

import java.util.*;

public class User {

    private String name;
    private double balance;
    private Map<String, Integer> portfolio;
    private List<Transaction> transactions;
    private double totalInvested;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.portfolio = new HashMap<>();
        this.transactions = new ArrayList<>();
        this.totalInvested = 0;
    }


    public void buyStock(Stock stock, int quantity) {

        if (stock == null) {
            System.out.println("Stock not found!");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Invalid quantity!");
            return;
        }

        double totalCost = stock.getPrice() * quantity;

        if (balance >= totalCost) {

            balance -= totalCost;
            totalInvested += totalCost;

            portfolio.put(stock.getSymbol(),
                    portfolio.getOrDefault(stock.getSymbol(), 0) + quantity);

            transactions.add(new Transaction(
                    stock.getSymbol(), quantity, stock.getPrice(), "BUY"));

            System.out.println("Stock purchased successfully!");

        } else {
            System.out.println("Insufficient balance!");
        }
    }


    public void sellStock(Stock stock, int quantity) {

        if (stock == null) {
            System.out.println("Stock not found!");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Invalid quantity!");
            return;
        }

        if (!portfolio.containsKey(stock.getSymbol())) {
            System.out.println("You don't own this stock!");
            return;
        }

        int ownedQty = portfolio.get(stock.getSymbol());

        if (ownedQty < quantity) {
            System.out.println("Not enough shares!");
            return;
        }

        double totalAmount = stock.getPrice() * quantity;

        balance += totalAmount;
        totalInvested -= totalAmount;

        int remainingQty = ownedQty - quantity;

        if (remainingQty == 0) {
            portfolio.remove(stock.getSymbol());
        } else {
            portfolio.put(stock.getSymbol(), remainingQty);
        }

        transactions.add(new Transaction(
                stock.getSymbol(), quantity, stock.getPrice(), "SELL"));

        System.out.println("Stock sold successfully!");
    }

    public void showPortfolio(Map<String, Stock> marketStocks) {

        System.out.println("\n========== PORTFOLIO ==========");

        double currentPortfolioValue = 0;

        if (portfolio.isEmpty()) {
            System.out.println("No stocks owned.");
        }

        for (String symbol : portfolio.keySet()) {

            int qty = portfolio.get(symbol);
            Stock stock = marketStocks.get(symbol);

            if (stock != null) {

                double currentPrice = stock.getPrice();
                double value = qty * currentPrice;

                currentPortfolioValue += value;

                System.out.printf("%s | Qty: %d | ₹%.2f | Value: ₹%.2f\n",
                        symbol, qty, currentPrice, value);
            }
        }

        System.out.println("---------------------------------");
        System.out.printf("Available Balance: ₹%.2f\n", balance);
        System.out.printf("Total Invested: ₹%.2f\n", totalInvested);
        System.out.printf("Current Value: ₹%.2f\n", currentPortfolioValue);

        double profitLoss = currentPortfolioValue - totalInvested;

        System.out.printf("Profit/Loss: ₹%.2f\n", profitLoss);
        System.out.printf("Net Worth: ₹%.2f\n", (balance + currentPortfolioValue));
        System.out.println("=================================");
    }


    public void showTransactions() {

        System.out.println("\n====== TRANSACTION HISTORY ======");

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (Transaction t : transactions) {
            t.display();
        }

        System.out.println("==================================");
    }

    public String getName() { return name; }
    public double getBalance() { return balance; }
    public double getTotalInvested() { return totalInvested; }
    public Map<String, Integer> getPortfolio() { return portfolio; }
    public List<Transaction> getTransactions() { return transactions; }
}

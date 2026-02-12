package model;

import java.time.LocalDateTime;

public class Transaction {

    private String stockSymbol;
    private int quantity;
    private double price;
    private String type; // BUY or SELL
    private LocalDateTime timestamp;

    public Transaction(String stockSymbol, int quantity, double price, String type) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    public void display() {
        System.out.printf("%s | %s | Qty: %d | ₹%.2f | %s\n",
                type, stockSymbol, quantity, price, timestamp);
    }

    public String toFileString() {
        return type + "," + stockSymbol + "," + quantity + "," + price;
    }

    public String getStockSymbol() { return stockSymbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public String getType() { return type; }
}

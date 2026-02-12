package service;

import model.Stock;
import java.util.*;

public class Market {

    private Map<String, Stock> stocks;

    public Market() {
        stocks = new HashMap<>();
    }

    public void addStock(Stock stock) {
        stocks.put(stock.getSymbol(), stock);
    }

    public void displayMarket() {
        System.out.println("\n------ Market Data ------");
        for (Stock stock : stocks.values()) {
            stock.display();
        }
    }

    public Stock getStock(String symbol) {
        return stocks.get(symbol.toUpperCase());
    }

    public void updateMarket() {
        for (Stock stock : stocks.values()) {
            stock.updatePrice();
        }
    }

    public Map<String, Stock> getAllStocks() {
        return stocks;
    }
}

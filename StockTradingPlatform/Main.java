import model.*;
import service.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Market market = new Market();
        market.addStock(new Stock("TCS", "Tata Consultancy", 3500));
        market.addStock(new Stock("INFY", "Infosys", 1500));
        market.addStock(new Stock("RELI", "Reliance", 2500));
        market.addStock(new Stock("NVDA" , "Nvidia" , 4000));

        User user = new User("Aarav", 100000);

        int choice;

        do {

            market.updateMarket(); // simulate price change

            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. Show Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    market.displayMarket();
                    break;

                case 2:
                    System.out.print("Enter Stock Symbol: ");
                    String buySymbol = sc.next();
                    System.out.print("Enter Quantity: ");
                    int buyQty = sc.nextInt();
                    user.buyStock(market.getStock(buySymbol), buyQty);
                    break;

                case 3:
                    System.out.print("Enter Stock Symbol: ");
                    String sellSymbol = sc.next();
                    System.out.print("Enter Quantity: ");
                    int sellQty = sc.nextInt();
                    user.sellStock(market.getStock(sellSymbol), sellQty);
                    break;

                case 4:
                    user.showPortfolio(market.getAllStocks());
                    break;

                case 5:
                    user.showTransactions();
                    break;

                case 6:
                	FileManager.saveUser(user);
                    System.out.println("Exiting... Data saved!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}

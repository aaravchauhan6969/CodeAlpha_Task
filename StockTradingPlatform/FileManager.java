package service;

import model.*;

import java.io.*;
import java.util.Map;

public class FileManager {

    private static final String FILE_NAME = "portfolio.txt";


    public static void saveUser(User user) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            writer.write("NAME:" + user.getName());
            writer.newLine();

            writer.write("BALANCE:" + user.getBalance());
            writer.newLine();

            writer.write("INVESTED:" + user.getTotalInvested());
            writer.newLine();

            writer.newLine();
            writer.write("PORTFOLIO");
            writer.newLine();

            for (Map.Entry<String, Integer> entry : user.getPortfolio().entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }

            writer.newLine();
            writer.write("TRANSACTIONS");
            writer.newLine();

            for (Transaction t : user.getTransactions()) {
                writer.write(t.toFileString());
                writer.newLine();
            }

            System.out.println("Data saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving file!");
            e.printStackTrace();
        }
    }

}

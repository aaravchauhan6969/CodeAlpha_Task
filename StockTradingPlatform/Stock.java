package model;
import java.util.Random;
public class Stock {

	private String symbol;
	private String companyName;
	private double price;
	
	public Stock(String symbol , String companyName , double price) {
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
	
	public void updatePrice() {
		Random random = new Random();
		double change = (random.nextDouble()-0.5)*50;
		price += change;
		
		if(price < 1) {
			price = 1;
		}
	}
	
	public void display() {
		System.out.printf("%-10s %-20s ₹%.2f\n" , symbol ,companyName ,price);
	}
}

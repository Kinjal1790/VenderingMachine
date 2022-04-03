package com.techelevator;

import com.techelevator.CateringItems.*;
import com.techelevator.ui.Menu;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static com.techelevator.auditLog.log.purchaseLog;

public class CaTEringCapstoneCLI {

	Map<String, Product> productMap = new TreeMap<>();
	BigDecimal money = new BigDecimal(0);
	BigDecimal zero = BigDecimal.ZERO;

	public static void main(String[] args) {
		CaTEringCapstoneCLI cli = new CaTEringCapstoneCLI();
		cli.run();
	}


	public void run() {
		try {
			while (true) {
				String choice = Menu.getHomeScreenChoice();

				if (choice.equals("d")) {
					processAvailableInventory();

				} else if (choice.equals("p")) {
					purchasingMenu();

				} else if (choice.equals("e")) {
					break;
				}
				else{
					System.out.println("Try again.");
				}
			}
		}
		catch(StringIndexOutOfBoundsException e){
			System.out.println("Try again.");

		}

	}


	public void processAvailableInventory() {

		File file = new File("catering.csv");
		try (Scanner fileReader = new Scanner(file)) {

			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				String[] columns = line.split(",");


				String slotIdentifier = columns[0];
				String itemName = columns[1];
				String productType = columns[2].toLowerCase();
				String price = columns[3];

				Product product;
				switch (productType) {
					case "munchy":
						product = new Munchy(slotIdentifier, itemName, price, productType);
						break;
					case "sandwich":
						product = new Sandwich(slotIdentifier, itemName, price, productType);
						break;
					case "drink":
						product = new Drink(slotIdentifier, itemName, price, productType);
						break;
					case "dessert":
					default:
						product = new Dessert(slotIdentifier, itemName, price, productType);
						break;
				}
				productMap.putIfAbsent(product.getSlot(), product);
			}
			System.out.println();
			displayingItems();
		}
		catch (IOException e) {
			e.getMessage();
		}
	}

	public void displayingItems() {

		Set<String> slots = productMap.keySet();
		for (String slot : slots) {
			System.out.println(productMap.get(slot));
		}
	}

	public void purchasingMenu() {

		while (true) {
			String choice = Menu.purchasingMenuDisplay(money);

			if ("m".equals(choice)) {
				BigDecimal moneyFed = zero;
				int moneyAmount = Menu.feedMoney();
				processingFedMoney(moneyFed, moneyAmount);
			}
			else if ("s".equals(choice)) {
				displayingItems();
				String itemSelected = Menu.selectItem();
				processingSelectedItem(itemSelected);

			} else if ("f".equals(choice)) {
				calculatingRemainingChange();
				break;

			}
		}
	}

	public void processingFedMoney(BigDecimal moneyFed, int moneyAmount) {

		if (moneyAmount == 1) {
			moneyFed = new BigDecimal(1);
			money = money.add(moneyFed);

		} else if (moneyAmount == 2) {
			moneyFed = new BigDecimal(5);
			money = money.add(moneyFed);

		} else if (moneyAmount == 3) {
			moneyFed = new BigDecimal(10);
			money = money.add(moneyFed);

		} else if (moneyAmount == 4) {
			moneyFed = new BigDecimal(20);
			money = money.add(moneyFed);
		}

		String message = "MONEY FED:		$" + moneyFed + " $" + money;
		purchaseLog(message);
	}

	public BigDecimal getMoney(){
		return money;
	}

	public void processingSelectedItem(String itemSelected) {

		if (!productMap.containsKey(itemSelected)) {
			System.out.println("That slot item is not available");

		}
		else if (productMap.containsKey(itemSelected)) {

			Product product = productMap.get(itemSelected);
			dispensingItemByVerifyingAvailablityOfItemAndMoney(product);
		}
	}

	public void dispensingItemByVerifyingAvailablityOfItemAndMoney(Product product) {
		double availableMoney = money.doubleValue();
		double price = product.getPrice().doubleValue();

		if (availableMoney >= price) {

			if (product.purchaseItem()) {
				System.out.println(product.dispense());
				removeMoney(product);
				String message = product.getItemName()  + "		" + product.getSlot() + " $" + (money.add(product.getPrice())) + " $" + money;
				purchaseLog(message);
			}
			else {
				System.out.println("Product sold out");
			}
		}

		else {
			System.out.println("Insufficient funds");
		}
	}

	private void removeMoney(Product product) {
		money = money.subtract(product.getPrice());
	}

	private void calculatingRemainingChange() {

		BigDecimal balance = money.multiply(new BigDecimal(100));
		int dollar = balance.intValue() / 100;
		balance = balance.subtract(new BigDecimal(dollar * 100));
		int quarter = balance.intValue() / 25;

		balance = balance.subtract(new BigDecimal(quarter * 25));
		int dime = balance.intValue() / 10;

		balance = balance.subtract(new BigDecimal(dime * 10));
		int nickle = balance.intValue() / 5;


		System.out.println("Here is the change: " + dollar + " dollar " + quarter + " quarter " + dime + " dime " + nickle + " nickle.");
		String message = "CHANGE GIVEN:		$" + money + " $" + zero;
		purchaseLog(message);

		money = zero;
	}

}




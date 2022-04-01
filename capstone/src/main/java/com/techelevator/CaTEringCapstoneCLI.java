package com.techelevator;

import com.techelevator.CateringItems.*;
import com.techelevator.ui.Menu;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static com.techelevator.auditLog.log.purchaseLog;

public class CaTEringCapstoneCLI {


//
//	private Menu menu;
//
//	public CaTEringCapstoneCLI(Menu menu) {
//		this.menu = menu;
//	}


	Map<String, Product> productMap = new TreeMap<>();
	BigDecimal money = new BigDecimal(0);
	BigDecimal zero = BigDecimal.ZERO;

	public static void main(String[] args) {
		//Menu menu = new Menu();
		CaTEringCapstoneCLI cli = new CaTEringCapstoneCLI();
		cli.run();
	}


	public void run() {

		while (true) {

			//  to do -- build out main menu
			String choice = Menu.getHOmeScreenChoice();
			if (choice.equals("d")) {
				processAvailableInventory();

			} else if (choice.equals("p")) {
				purchasingManu();

				// feed money; select item, finish transaction
			} else if (choice.equals("e")) {
				break;
			}
		}

	}

	public void processAvailableInventory() {


		File file = new File("catering.csv");
		try (Scanner fileReader = new Scanner(file)) {

			while (fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				String[] columns = line.split(",");


				String slot = columns[0];
				String itemName = columns[1];
				String productType = columns[2].toLowerCase();
				String price = columns[3];


				Product product;
				switch (productType) {
					case "munchy":
						product = new Munchy(slot, itemName, price, productType);
						break;
					case "sandwich":
						product = new Sandwich(slot, itemName, price, productType);
						break;
					case "drink":
						product = new Drink(slot, itemName, price, productType);
						break;
					case "dessert":
					default:
						product = new Dessert(slot, itemName, price, productType);
						break;
				}

				productMap.putIfAbsent(product.getSlot(), product);

			}
			System.out.println();

			displayingItems();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public void displayingItems() {
		Set<String> slots = productMap.keySet();
		for (String slot : slots) {
			System.out.println(productMap.get(slot));
		}
	}


	public void purchasingManu() {
		while (true) {
			String choice = Menu.purchasingProcessMenuDisplay(money);
			

			if ("m".equals(choice)) {
				BigDecimal moneyFeed = zero;
				int moneyAmount = Menu.addMoney();
				if (moneyAmount == 1) {
					moneyFeed = new BigDecimal(1);
					money = money.add(moneyFeed);

				} else if (moneyAmount == 2) {
					moneyFeed = new BigDecimal(5);
					money = money.add(moneyFeed);

				} else if (moneyAmount == 3) {
					moneyFeed = new BigDecimal(10);
					money = money.add(moneyFeed);

				} else if (moneyAmount == 4) {
					moneyFeed = new BigDecimal(20);
					money = money.add(moneyFeed);
				}
				String message = "MONEY FED:		$" + moneyFeed + " $" + money;
				purchaseLog(message);

			} 
			else if ("s".equals(choice)) {
				displayingItems();
				String itemSelected = Menu.selectItem();
				printingSelectedItem(itemSelected);

				// make a method to select item
			} else if ("f".equals(choice)) {
				// make a method to finish transaction
				calculatingRemainingChange();

				break;

			}
		}
	}


	public void printingSelectedItem(String itemSelected) {
		if (!productMap.containsKey(itemSelected)) {
			System.out.println("That slot item is not available");
		} else if (productMap.containsKey(itemSelected)) {

			Product product = productMap.get(itemSelected);
			double available = money.doubleValue();
			double price = product.getPrice().doubleValue();

			if (available >= price) {
				if (product.purchaseItem()) {

					System.out.println(product.dispense());

					money = money.subtract(product.getPrice());

					String message = product.getItemName()  + "		" + product.getSlot() + " $" + (money.add(product.getPrice())) + " $" + money;
					purchaseLog(message);


				} else {
					System.out.println("Product sold out");
				}
			} else {
				System.out.println("Insufficient funds");
			}
		}
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




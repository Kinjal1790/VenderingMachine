package com.techelevator;

import com.techelevator.CateringItems.*;
import com.techelevator.ui.Menu;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CaTEringCapstoneCLI {


//
//	private Menu menu;
//
//	public CaTEringCapstoneCLI(Menu menu) {
//		this.menu = menu;
//	}


	Map<String, Product> productMap = new TreeMap<>();


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
			}

			else if (choice.equals('p')) {
				// purchasing product

				purchasingManu();
				// feed money; select item, finish transaction
			}

			else if (choice.equals("x")) {
				break;
			}

			System.out.println("Good Bye!!");
		}
	}


	private void processAvailableInventory() {


		File file = new File("catering.csv");
		try(Scanner fileReader = new Scanner(file)) {

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
						product = new Munchy(slot, itemName, price);
						break;
					case "sandwich":
						product = new Sandwich(slot, itemName, price);
						break;
					case "drink":
						product = new Drink(slot, itemName, price);
						break;
					case "dessert":
					default:
						product = new Dessert(slot, itemName, price);
						break;
				}

				productMap.putIfAbsent(product.getSlot(), product);

			}
			Menu.getSelectedProductChoice(productMap);
		}

		catch(IOException e){
				e.getMessage();
			}
		}

		private void purchasingManu() {

			while (true) {
				String choice = Menu.purchasingProcessMenuDisplay();

				switch (choice) {
					case "m":
						// make a method that will do feed money
						moneyinput();

						break;
					case "s":
						// make a method to select item
						break;
					case "f":
						// make a method to finish transaction
						break;
				}

			}
		}

	private void moneyinput() {

		String balance = Menu.moneyInputByUser();
		if (Integer.parseInt(balance)%1==0){
			return

		}

	}

}






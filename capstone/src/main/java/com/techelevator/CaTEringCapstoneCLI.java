package com.techelevator;

import com.techelevator.ui.Menu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CaTEringCapstoneCLI {


//
//	private Menu menu;
//
//	public CaTEringCapstoneCLI(Menu menu) {
//		this.menu = menu;
//	}


	List<String[]> products = new ArrayList<>();

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
			//	String productType = columns[2];
				String price = columns[3];
				int remainingQuantity = 7;


			}

			products.add();

		}

		catch(IOException e){
				e.getMessage();
			}
		}
	}

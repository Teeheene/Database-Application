package view;

import model.*;
import java.util.Scanner;

public class CLIView implements View {
	Scanner input = new Scanner(System.in);
	EnthusiastView enthusiastView;

	public CLIView() {
		enthusiastView = new CLIEnthusiastView();	
	}

	@Override 
	public String showMenuScreen() {
		System.out.println("_______________________");	
		System.out.println("BASKETBALL APPLICATION");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Exit");
		System.out.println("_______________________");	

		switch(getInput(1,3)) {
			case 1: return "login";
			case 2: return "register";
			case 3: return "exit";
		}

		return null;
	}	

	private int getInput(int startRange, int endRange) {
		int userInput = -1;
		String prefix = "input # ";

		do {
			try {
				System.out.print(prefix);
				userInput = input.nextInt();	
				if(userInput < startRange || userInput > endRange) {
					System.out.println(prefix + "Out of range!");
				}
			} catch(Exception E) {
				System.out.println(prefix + "Please enter a valid input!");
			}
			input.nextLine();
		} while(userInput < startRange || userInput > endRange);

		return userInput;
	}

	@Override
	public LoginBuilder showLoginScreen() {
		System.out.println("_______________________");	
		System.out.println("LOGIN");
		System.out.print("Username: ");
		String username = input.nextLine();	
		System.out.print("Password: ");
		String password = input.nextLine();
		System.out.println("Type: ");
		System.out.println("1. Enthusiast");
		System.out.println("2. Player");
		System.out.println("3. Coach");
		System.out.println("4. Manager");	
		
		String type = new String();
		switch(getInput(1,4)) {
			case 1: type = "enthusiast";
					  break;
			case 2: type = "player";
					  break;
			case 3: type = "coach";
					  break;
			case 4: type = "manager";
					  break;
		}
		System.out.println("_______________________");	

		return new LoginBuilder(username, password, type);
	} 

	@Override
	public String showEnthusiastProfile(Enthusiast enthusiast) {
		enthusiastView.showEnthusiast(enthusiast);
		System.out.println("");
		System.out.println("_______________________");	
		System.out.println("OPTIONS");
		System.out.println("1. Edit");
		System.out.println("2. Delete");
		System.out.println("3. Exit");

		switch(getInput(1,3)) {
			case 1: return "edit";
			case 2: return "delete";
			case 3: return "exit";
		}
		
		return null;
	} 
}

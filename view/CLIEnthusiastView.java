package view;

import java.util.Scanner;
import model.*;

public class CLIEnthusiastView implements EnthusiastView {
	Scanner input = new Scanner(System.in);

	@Override
	public Enthusiast createEnthusiast() {
		System.out.println("_______________________");	
		System.out.println("REGISTER");
		System.out.print("Username: ");
		String username = input.nextLine();
		System.out.print("Last Name: ");
		String lastName = input.nextLine();
		System.out.print("First Name: ");
		String firstName = input.nextLine();
		System.out.print("Middle Name: ");
		String middleName = input.nextLine();
		System.out.print("Date Of Birth: ");
		String dateOfBirth = input.nextLine();
		System.out.print("Sex: ");
		String sex = input.nextLine();
		System.out.println("");
		System.out.print("Account Password: ");
		String password = input.nextLine();
		System.out.println("_______________________");	
		
		return new Enthusiast(username, lastName, firstName, middleName, dateOfBirth, sex, password);
	}

	@Override
	public void showEnthusiast(Enthusiast enthusiast) {
		System.out.println("_______________________");	
		System.out.println("PROFILE");	
		System.out.println(enthusiast.getUsername() + " ID#" + enthusiast.getID());
		System.out.println("Name: " + enthusiast.getFirstName() + " " + enthusiast.getMiddleName() + ". " + enthusiast.getLastName());
		System.out.println("Birthday: " + enthusiast.getDateOfBirth());
		System.out.println("Sex: " + enthusiast.getSex());
		System.out.println("_______________________");	
	}

	//will probably change this to something
	//that passes only text and not just outright changes the data
	//but for another time :p
	//IMPLEMENT A BUILDER PLEASE :sob:
	@Override
	public Enthusiast showUpdate() {
		Enthusiast enthusiast = new Enthusiast();
		System.out.println("_______________________");	
		System.out.println("UPDATE");
		System.out.println("Type 'same' to not edit");
		System.out.print("Username: ");
		String username = input.nextLine();
		if(!username.equalsIgnoreCase("same")) { enthusiast.setUsername(username); }
		System.out.print("Last Name: ");
		String lastName = input.nextLine();
		if(!lastName.equalsIgnoreCase("same")) { enthusiast.setLastName(lastName); }
		System.out.print("First Name: ");
		String firstName = input.nextLine();
		if(!firstName.equalsIgnoreCase("same")) { enthusiast.setFirstName(firstName); }
		System.out.print("Middle Name: ");
		String middleName = input.nextLine();
		if(!middleName.equalsIgnoreCase("same")) { enthusiast.setMiddleName(middleName); }
		System.out.print("Date of Birth: ");
		String dateOfBirth = input.nextLine();
		if(!dateOfBirth.equalsIgnoreCase("same")) { enthusiast.setDateOfBirth(dateOfBirth); }
		System.out.print("Sex: ");
		String sex = input.nextLine();
		if(!sex.equalsIgnoreCase("same")) { enthusiast.setSex(sex); }

		return enthusiast;
	}

	@Override
	public boolean showDelete(Enthusiast enthusiast) {
		System.out.println("_______________________");	
		System.out.println("DELETE CONFIRMATION");
		System.out.println("Please type 'yes' to ");
		System.out.println("confirm deletion");
		System.out.println("_______________________");	
		System.out.print("> ");
		if(input.nextLine().equals("yes")) {
			System.out.println("Deleting Account...");
			return true;
		}
		System.out.println("Failed to Delete Account...");
		return false;
	}
}


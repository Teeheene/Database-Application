package view;

import java.util.Scanner;
import java.util.ArrayList;
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
		System.out.print("Year Of Birth: ");
		int birthYear = input.nextInt();
		System.out.print("Month Of Birth: ");
		int birthMonth = input.nextInt();
		System.out.print("Day Of Birth: ");
		int birthDay = input.nextInt();
		input.nextLine();
		System.out.print("Sex: ");
		String sex = input.nextLine();
		System.out.println("_______________________");	
		
		return new Enthusiast(
			username, 
			lastName, firstName, middleName, 
			sex, 
			new Timestamp(birthYear, birthMonth, birthDay)
		);
	}

	@Override
	public void showEnthusiast(Enthusiast enthusiast) {
		System.out.println("_______________________");	
		System.out.println("PROFILE");	
		System.out.println(enthusiast.getUsername() + " ID#" + enthusiast.getID());
		System.out.println("Name: " + enthusiast.getFirstName() + " " + enthusiast.getMiddleName() + ". " + enthusiast.getLastName());
		System.out.print("Birthday: ");
		enthusiast.getDateOfBirth().displayDate();
		System.out.println("Sex: " + enthusiast.getSex());
		System.out.println("_______________________");	
	}

	@Override
	public void showAllEnthusiast(ArrayList<Enthusiast> enthusiast) {
		for(Enthusiast e : enthusiast) {
			if(e == null) { continue; }
			showEnthusiast(e);
		}
	}

	//will probably change this to something
	//that passes only text and not just outright changes the data
	//but for another time :p
	//IMPLEMENT A BUILDER PLEASE :sob:
	//update 10/27/25 - this is so stupid pls update (im not doing it hehe)
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
		if(!dateOfBirth.equalsIgnoreCase("same")) { 
			System.out.print("Year of Birth: ");
			int birthYear = input.nextInt();
			System.out.print("Month of Birth: ");
			int birthMonth = input.nextInt();
			System.out.print("Day of Birth: ");
			int birthDay = input.nextInt();
			enthusiast.setDateOfBirth(new Timestamp(birthYear, birthMonth, birthDay)); }
		System.out.print("Sex: ");
		String sex = input.nextLine();
		if(!sex.equalsIgnoreCase("same")) { enthusiast.setSex(sex); }

		return enthusiast;
	}

	public int deleteEnthusiast() {
		System.out.println("_______________________");	
		System.out.println("DELETE");
		System.out.println("Type 0 to cancel.");
		System.out.print("Type ID to delete: ");
		int ID = input.nextInt();
		input.nextLine();
		return ID;
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


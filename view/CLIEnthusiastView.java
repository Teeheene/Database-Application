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

	@Override
	public void showUpdate(Enthusiast enthusiast) {
		System.out.println("To be implemented");
	}

	@Override
	public void showDelete(Enthusiast enthusiast) {
		System.out.println("To be implemented");
	}
}


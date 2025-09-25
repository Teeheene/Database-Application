package view;

import java.util.Scanner;
import model.*;

public class CLIEnthusiastView implements EnthusiastView {
	Scanner input = new Scanner(System.in);

	@Override
	public Enthusiast createEnthusiast() {
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
		
		return new Enthusiast(username, lastName, firstName, middleName, dateOfBirth, sex);
	}

	@Override
	public void showEnthusiast(Enthusiast enthusiast) {
		System.out.println("To be implemented");
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

package controller;

import model.*;
import view.*;

public class MainController {
	private View view;
	private EnthusiastView enthusiastView;
	private EnthusiastManagement enthusiastModel;
	
	public MainController(View view, EnthusiastView enthusiastView) {
		this.view = view;
		this.enthusiastView = enthusiastView;

		enthusiastModel = new EnthusiastManagement();
	}
	
	public void start() {
		boolean exit = false;
		boolean login = false;

		LoginBuilder loginDetails = new LoginBuilder();
		Enthusiast enthusiast = new Enthusiast();
		
		do {
			String command = view.showMenuScreen();

			switch(command) {
				case "login":
					loginDetails = view.showLoginScreen();
					login = true;	
					break;
				case "register": 
					enthusiast = enthusiastView.createEnthusiast();
					enthusiastModel.addEnthusiast(enthusiast);
					enthusiastView.showEnthusiast(enthusiast);
					break;
				case "exit":
					exit = true; 
					break;
			}
			
			if(login) {
				switch(loginDetails.getType()) {
					case "enthusiast": 
						enthusiast = enthusiastModel.verifyLogin(loginDetails);
						if(enthusiast != null) { enthusiastProfile(enthusiast); }
						break;
					case "player":
						break;
					case "coach":
						break;
					case "manager":
						break;
				}
				login = false;
			}

		} while(!exit);
	}

	public void enthusiastProfile(Enthusiast user) {
		boolean exit = false;
		do {
			String option = view.showEnthusiastProfile(user);
			switch(option) {
				case "edit":
					//passing it to model for a future 
					//sql implementation update
					Enthusiast newUser = enthusiastView.showUpdate(); 
					user = enthusiastModel.updateEnthusiast(user, newUser);
					break;
				case "delete": 
					if(enthusiastView.showDelete(user)) { 
						enthusiastModel.deleteEnthusiast(user);
						exit = true;
						break;
					}
					else 
						break;
				case "exit":
					exit = true;
					break;
			}
		} while(!exit);
	}
}

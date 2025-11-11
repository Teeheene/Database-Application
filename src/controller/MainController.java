package controller;

import model.*;
import view.*;

public class MainController {
	private View view;
	private EnthusiastView enthusiastView;
	private EnthusiastManagement enthusiastModel;
	private PlayerView playerView;
	private PlayerManagement playerModel;
	private PlayerController playerController;
	
	public MainController(View view, EnthusiastView enthusiastView, PlayerView playerView) {
		this.view = view;
		this.enthusiastView = enthusiastView;
		this.playerView = playerView;

		enthusiastModel = new EnthusiastManagement();
		playerModel = new PlayerManagement();
		playerController = new PlayerController(playerModel, playerView);
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
					//viewRegisterScreen();
					//to remove enthusiast only creation
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
						enthusiast = enthusiastModel.searchEnthusiastByID(loginDetails.getID());
						if(enthusiast != null) { enthusiastProfile(enthusiast); }
						break;
					case "player": playerController.run();
						break;
					case "coach":
						break;
					case "manager":
						break;
					case "admin":
						if(loginDetails.getPassword().equals("admin123")) {
							adminProfile();
						}
						break;
				}
				login = false;
			}

		} while(!exit);
	}

	public void adminProfile() {
		boolean exit = false;
		do {
			String option = view.showAdminProfile();
			switch(option) {
				case "enthusiasts":
					adminCrud("Enthusiast");
					break;	
				case "logout":
					exit = true;
					break;
			}
		} while(!exit);
	}

	public void adminCrud(String type) {
		boolean exit = false;
		Enthusiast enthusiast = new Enthusiast();
		do {
			String option = view.showAdminCrud(type);
			switch(option) {
				case "create":
					if(type.equals("Enthusiast")) {
						enthusiast = enthusiastView.createEnthusiast();
						enthusiastModel.addEnthusiast(enthusiast);
						enthusiastView.showEnthusiast(enthusiast);
					}
					break;
				case "view":
					if(type.equals("Enthusiast")) {
						enthusiastView.showAllEnthusiast(enthusiastModel.getEnthusiasts());
					}
					break;
				case "update":
					if(type.equals("Enthusiast")) {
						//update
					}
					break;
				case "delete":
					if(type.equals("Enthusiast")) {
						int ID = enthusiastView.deleteEnthusiast();
						if(enthusiastView.showDelete(enthusiastModel.searchEnthusiastByID(ID)))
							enthusiastModel.deleteEnthusiast(ID);
						exit = true;
					}
					break;
				case "exit":
					exit = true;
					break;
			}
		} while(!exit);
	}

	public void enthusiastProfile(Enthusiast user) {
		boolean exit = false;
		do {
			String option = view.showEnthusiastProfile(user);
			switch(option) {
				case "edit":
					Enthusiast newUser = enthusiastView.showUpdate(); 
					user = enthusiastModel.updateEnthusiast(user, newUser);
					break;
				case "delete": 
					if(enthusiastView.showDelete(user)) { 
						enthusiastModel.deleteEnthusiast(user.getID());
						exit = true;
						break;
					}
					else 
						break;
				case "search":
					
				case "exit":
					exit = true;
					break;
			}
		} while(!exit);
	}
}

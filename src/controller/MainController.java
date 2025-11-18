package controller;

import model.*;
import view.*;
import java.util.ArrayList;

public class MainController {
	private GUIView view;
	//admin
	private GUIAdminView adminView;
	private AdminController adminController;
	//enthusiast
	private GUIEnthusiastView enthusiastView;
	private EnthusiastController enthusiastController;
	private EnthusiastManagement enthusiastModel;
	//player
	private GUIPlayerView playerView;
	private PlayerManagement playerModel;
	private PlayerController playerController;

	public MainController(GUIView view) {
		//gui inits
		this.view = view;

		this.adminView = new GUIAdminView(); 
		adminController = new AdminController(view, adminView);	

		this.enthusiastView = new GUIEnthusiastView();
		enthusiastController = new EnthusiastController(view, enthusiastView);
		enthusiastModel = new EnthusiastManagement();
		
		this.playerView = new GUIPlayerView();
		playerModel = new PlayerManagement();
		playerController = new PlayerController(view, playerView);
	}
	
	public void handleMenu(String option) {
		switch(option) {
			case "admin":
				view.adminLoginPanel();
				break;
			case "login":
				view.loginPanel();
				break;
			case "register":
				view.registerPanel();
				break;
		}
	}

	public boolean handleAdminLogin(String password) {
		if(password.equals("admin123")) {
			adminView.setListeners(adminController);
			adminView.start();
			
			//transfer to sub-view (admin profile)
			view.hide();
			adminView.show();
		} else {
			return false;
		}

		return true;
	}

	public String handleEnthusiastLogin(int ID) {
		Enthusiast enthusiast = enthusiastModel.searchEnthusiastByID(ID);
		if(enthusiast == null) return "ERR|DNE"; //does not exist
		if(!enthusiast.getStatus()) return "ERR|DA"; //deactivated
		enthusiastView.setListeners(enthusiastController);
		enthusiastView.start(enthusiast);
		enthusiastView.profilePanel();
		view.hide();
		enthusiastView.show();

		return null;
	}

	public void handleEnthusiastRegister(Enthusiast enthusiast) {
		enthusiastModel.addEnthusiast(enthusiast);
	}

	public boolean handlePlayerLogin(int ID) {
		Player player = playerModel.searchPlayer(ID);
		if(player == null) return false;

		playerView.setListeners(playerController);
		playerView.start(player);
		playerView.profilePanel();
		view.hide();
		playerView.show();

		return true;
	}

	public void handlePlayerRegister(Player player) {
		playerModel.addPlayer(player);
	}
	
	/*
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
					int ID = enthusiastModel.addEnthusiast(enthusiast);
					enthusiastView.showEnthusiast(enthusiastModel.searchEnthusiastByID(ID));
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
					case "tournament":
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
				case "players":
					adminCrud("Players");
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
						enthusiastView.showAllEnthusiast(enthusiastModel.getEnthusiasts());
						int ID = view.showAdminUpdate();
						Enthusiast newUser = enthusiastView.showUpdate();
						Enthusiast user = enthusiastModel.searchEnthusiastByID(ID);
						enthusiastView.showEnthusiast(enthusiastModel.updateEnthusiast(user, newUser));
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
				case "check following":
					break;
				case "check likes":
					break;
				case "engage":
					if(enthusiastEngagement(user)) {
						exit = true;
					}
					break;
				case "exit":
					exit = true;
					break;
			}
		} while(!exit);
	}

	public boolean enthusiastEngagement(Enthusiast user) {
		boolean exit = false;
		int currentFeedIndex = 0;
		do {
			String option = engagementView.showEngagementPage();	
			switch(option) {
				case "scroll up":
					if(engagementView.showProfiles("up", engagementModel.getFeed(), currentFeedIndex))
						currentFeedIndex++;
					break;
				case "scroll down":
					if(engagementView.showProfiles("down", engagementModel.getFeed(), currentFeedIndex))
						currentFeedIndex--;
					break;
				case "like":
					engagementView.engageProfile("like");
					break;
				case "follow":
					engagementView.engageProfile("follow");
					break;
				case "profile":
					exit = true;
					break;
				case "exit":
					return true;
			}
		} while(!exit);

		return false;
	}
	*/
}

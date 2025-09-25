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
		Enthusiast enthusiast = new Enthusiast();
		
		do {
			String command = view.showMenuScreen();

			switch(command) {
				case "login":
					LoginBuilder loginDetails = new LoginBuilder();
					loginDetails = view.showLoginScreen();
					switch(loginDetails.getType()) {
						case "enthusiast": 
							enthusiast = enthusiastModel.verifyLogin(loginDetails);
							if(enthusiast != null) {
								view.showEnthusiastProfile(enthusiast);
							}
					}
					break;
				case "register": 
					enthusiast = enthusiastView.createEnthusiast();
					enthusiastModel.addEnthusiast(enthusiast);
					enthusiastView.showEnthusiast(enthusiast);
					break;
				case "exit":
					exit = true; 
			}
		} while(!exit);
	}
}

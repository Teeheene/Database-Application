package controller;

import model.*;
import view.*;

public class AdminController {
	private GUIView prevView;
	private GUIAdminView view;

	private EnthusiastManagement enthusiastModel;
	
	public AdminController(GUIView prevView, GUIAdminView view) {
		this.prevView = prevView;
		this.view = view;	

		enthusiastModel = new EnthusiastManagement();
	}	

	public void handleMenu(String option) {
		switch(option) {
			case "enthusiast":
				System.out.println("Opening enthusiast dashboard...");
				view.enthusiastDashboardPanel();
				break;
			case "exit":
				view.dispose();
				prevView.menuPanel();
				prevView.show();
				break;
		}
	}

	public void handleEnthusiast(String option) {
		switch(option) {
			case "create":
				System.out.println("Opening create...");
				view.createEnthusiastPanel();
				break;
			case "viewAll":
				break;
			case "update":
				break;
			case "delete":
				break;
			case "report":
				break;
			case "exit":
				view.dashboardPanel();
				break;
		}
	}

	public void addEnthusiast(Enthusiast enthusiast) {
		enthusiastModel.addEnthusiast(enthusiast);
	}
}

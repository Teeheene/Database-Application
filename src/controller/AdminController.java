package controller;

import java.util.LinkedHashMap;
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
				System.out.println("Opening view all...");
				view.viewAllEnthusiastPanel(getEnthusiastInformation());
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

	public void showEnthusiast(int ID) {
		System.out.println("ts is running naman but ure bobo, heres the key: " + ID);
		Enthusiast enthusiast = enthusiastModel.searchEnthusiastByID(ID);
		view.viewEnthusiastPanel(enthusiast);
	}

	//admin helper
	public LinkedHashMap<String, String> getEnthusiastInformation() {
		LinkedHashMap<String, String> information = new LinkedHashMap<>();

		for(Enthusiast e : enthusiastModel.getEnthusiasts()) {
			if(e == null) { continue; }
			String key = String.valueOf(e.getID());
			String value = e.getSimpleInfo();
			information.put(key, value);
			System.out.println(e.getSimpleInfo());
		}

		return information;
	}
}

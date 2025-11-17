package controller;

import java.util.LinkedHashMap;
import model.*;
import view.*;

public class AdminController {
	private GUIView prevView;
	private GUIAdminView view;

	private EnthusiastManagement enthusiastModel;

	public AdminController() {}
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
				view.createEnthusiastPanel();
				break;
			case "viewAll":
				view.viewAllEnthusiastPanel(getEnthusiastInformation());
				break;
			case "viewDeleted":
				break;
			case "viewActive":
				break;	
			case "update":
				view.searchUpdateEnthusiastPanel();
				break;
			case "delete":
				view.searchDeleteEnthusiastPanel();
				break;
			case "report":
				view.viewReportEnthusiastPanel();
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
		Enthusiast enthusiast = enthusiastModel.searchEnthusiastByID(ID);
		view.viewEnthusiastPanel(enthusiast);
	}

	public boolean updateEnthusiastView(int ID) {
		Enthusiast enthusiast = enthusiastModel.searchEnthusiastByID(ID);
		if(enthusiast == null) return false; 
		view.updateEnthusiastPanel(enthusiast);
		return true;
	}
	
	public void updateEnthusiast(Enthusiast updatedEnthusiast) {
		Enthusiast enthusiast = enthusiastModel.updateEnthusiast(updatedEnthusiast);
		view.viewEnthusiastPanel(enthusiast);
	}
		
	public boolean deleteEnthusiastView(int ID) {
		Enthusiast enthusiast = enthusiastModel.searchEnthusiastByID(ID);
		if(enthusiast == null) return false; 
		view.deleteEnthusiastPanel(enthusiast);
		return true;
	}
	
	public void toggleEnthusiast(Enthusiast enthusiast) {
		enthusiastModel.toggleEnthusiast(enthusiast.getID());
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

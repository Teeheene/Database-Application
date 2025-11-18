package controller;

import java.util.LinkedHashMap;
import java.util.ArrayList;
import model.*;
import view.*;

public class AdminController {
	private GUIView prevView;
	private GUIAdminView view;

	private EnthusiastManagement enthusiastModel;
	private EngagementManagement engagementModel;
    private TournamentManagement tournamentModel;

	public AdminController() {}
	public AdminController(GUIView prevView, GUIAdminView view) {
		this.prevView = prevView;
		this.view = view;	

		enthusiastModel = new EnthusiastManagement();
		engagementModel = new EngagementManagement();
        tournamentModel = new TournamentManagement();
	}	

	public void handleMenu(String option) {
		switch(option) {
			case "enthusiast":
				System.out.println("Opening enthusiast dashboard...");
				view.enthusiastDashboardPanel();
				break;
            case "tournament":
                System.out.println("Opening tournament dashboard...");
                view.tournamentDashboardPanel();
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
			case "home":
				handleMenu("enthusiast");
				break;
			case "create":
				view.createEnthusiastPanel();
				break;
			case "viewAll":
				view.viewAllEnthusiastPanel(getEnthusiastInformation("all"));
				break;
			case "viewActive":
				view.viewAllEnthusiastPanel(getEnthusiastInformation("active"));
				break;
			case "viewInactive":
				view.viewAllEnthusiastPanel(getEnthusiastInformation("inactive"));
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

	public void showEnthusiast(int ID, String status) {
		Enthusiast enthusiast = enthusiastModel.searchEnthusiastByID(ID);
		view.viewEnthusiastPanel(enthusiast, status, "viewAll");
	}

	public boolean updateEnthusiastView(int ID) {
		Enthusiast enthusiast = enthusiastModel.searchEnthusiastByID(ID);
		if(enthusiast == null) return false; 
		view.updateEnthusiastPanel(enthusiast);
		return true;
	}
	
	public void updateEnthusiast(Enthusiast updatedEnthusiast) {
		Enthusiast enthusiast = enthusiastModel.updateEnthusiast(updatedEnthusiast);
		view.viewEnthusiastPanel(enthusiast, "active", "home");
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
	public LinkedHashMap<String, String> getEnthusiastInformation(String status) {
		LinkedHashMap<String, String> information = new LinkedHashMap<>();

		for(Enthusiast e : enthusiastModel.getEnthusiasts()) {
			if(e == null) { continue; }

			String key = String.valueOf(e.getID());
			key += "-"; //delimiter
			if(e.getStatus())
				key += "active";
			else
				key += "inactive";
			
			if(status.equals("active") && !e.getStatus()) {
				continue;
			}
			if(status.equals("inactive") && e.getStatus()) {
				continue;
			}
				
			String value = e.getSimpleInfo();
			information.put(key, value);
			System.out.println(e.getSimpleInfo());
		}

		return information;
	}

	public String[][] generateEnthusiastReport() {
		ArrayList<Enthusiast> enthusiasts = enthusiastModel.getActiveEnthusiasts();
		String data[][] = new String[enthusiasts.size()][9];
		int i;
		for(i = 0; i < enthusiasts.size(); i++) {
			if(enthusiasts.get(i) == null) { continue; }
			int id = enthusiasts.get(i).getID();
			data[i][0] = String.valueOf(id);
			data[i][1] = enthusiasts.get(i).getFullName();
			data[i][2] = enthusiasts.get(i).getSex();
			data[i][3] = String.valueOf(engagementModel.getTotalEngagement(id));  
			data[i][4] =  String.valueOf(engagementModel.getTotalPlayerEngagement(id));  
			data[i][5] =  String.valueOf(engagementModel.getTotalCoachEngagement(id));  
			data[i][6] =  String.valueOf(engagementModel.getTotalTournamentEngagement(id));  
			data[i][7] =  String.valueOf(engagementModel.getAvgEngagements(id));
			data[i][8] =  String.valueOf(engagementModel.getLastEngagement(id));
		}

		return data;
	}

    // ========= TOURNAMENT============

    public void handleTournament(String option) {
        switch(option) {
            case "home":
                handleMenu("tournament");
                break;
            case "create":
                view.createTournamentPanel();
                break;
            case "viewAll":
                view.viewAllTournamentPanel(getTournamentInformation());
                break;
            case "update":
                view.searchUpdateTournamentPanel();
                break;
            case "delete":
                view.searchDeleteTournamentPanel();
                break;
            case "report":
                view.viewReportTournamentPanel();
                break;
            case "exit":
                view.dashboardPanel();
                break;
        }
    }

    // ========== Tournament CRUD logic ==========

    public void addTournament(Tournament tournament) {
        int newID = tournamentModel.addTournament(tournament);
        tournament.setTournamentID(newID);
    }

    public void showTournament(int tournamentID) {
        Tournament tournament = tournamentModel.searchTournamentByID(tournamentID);
        view.viewTournamentPanel(tournament);
    }

    public boolean updateTournamentView(int tournamentID) {
        Tournament tournament = tournamentModel.searchTournamentByID(tournamentID);
        if(tournament == null) return false;
        view.updateTournamentPanel(tournament);
        return true;
    }

    public void updateTournament(Tournament updatedTournament) {
        Tournament tournament = tournamentModel.updateTournament(updatedTournament);
        view.viewTournamentPanel(tournament);
    }

    public boolean deleteTournamentView(int tournamentID) {
        Tournament tournament = tournamentModel.searchTournamentByID(tournamentID);
        if(tournament == null) return false;
        view.deleteTournamentPanel(tournament);
        return true;
    }

    public void deleteTournament(Tournament tournament) {
        tournamentModel.deleteTournament(tournament.getTournamentID());
    }

    public LinkedHashMap<String, String> getTournamentInformation() {
        LinkedHashMap<String, String> information = new LinkedHashMap<>();

        for (Tournament t : tournamentModel.getTournaments()) {
            if (t == null) continue;

            String key = String.valueOf(t.getTournamentID());
            String value = t.getTournamentName() + " - Season " + t.getSeasonYear() +
                    " (" + t.getTournamentType() + ")";

            information.put(key, value);
        }

        return information;
    }







}


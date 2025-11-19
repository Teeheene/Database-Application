package controller;

import java.util.LinkedHashMap;
import java.util.Comparator;
import java.util.ArrayList;
import model.*;
import view.*;

public class AdminController {
	private GUIView prevView;
	private GUIAdminView view;

	private EnthusiastManagement enthusiastModel;
	private EngagementManagement engagementModel;
	private ReportsManagement reportModel;
    private TournamentManagement tournamentModel;
	private PlayerManagement playerModel;
	private CoachManagement coachModel;

	public AdminController() {}
	public AdminController(GUIView prevView, GUIAdminView view) {
		this.prevView = prevView;
		this.view = view;	

		//core
		enthusiastModel = new EnthusiastManagement();
		playerModel = new PlayerManagement();
		//transactions
		engagementModel = new EngagementManagement();
		tournamentModel = new TournamentManagement();
		coachModel = new CoachManagement();
		//reports
		reportModel = new ReportsManagement();
	}	

	public void handleMenu(String option) {
		switch(option) {
			case "enthusiast":
				System.out.println("Opening enthusiast dashboard...");
				view.enthusiastDashboardPanel();
				break;
			case "player":
				System.out.println("Opening player dashboard...");
				view.playerDashboardPanel();
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

	public boolean generatePdfEnthusiastReport() {
        return reportModel.engagementReport(generateEnthusiastReport());
	}

	// ------------------ COACH -------------------
	public void handleCoach(String option) {
		switch (option) {
			case "home":
				handleMenu("coach");
				break;
			case "create":
				view.createCoachPanel();
				break;
			case "viewAll":
				view.viewAllCoachesPanel(getCoachInformation("all"));
				break;
			case "viewCurrent":
				view.viewAllCoachesPanel(getCoachInformation("current"));
				break;
			case "viewPast":
				view.viewAllCoachesPanel(getCoachInformation("past"));
				break;
			case "update":
				view.searchUpdateCoachPanel();
				break;
			case "delete":
				view.searchDeleteCoachPanel();
				break;
			case "report":
				view.viewReportCoachPanel();
				break;
			case "exit":
				view.dashboardPanel();
				break;
		}
	}

	public void addCoach(Coach coach) {
		coachModel.addCoach(coach);
	}

	public void showCoach(int ID, String status) {
		Coach coach = coachModel.searchCoach(ID);
		view.viewCoachPanel(coach, status, "viewAll");
	}

	public boolean updateCoachView(int ID) {
		Coach coach = coachModel.searchCoach(ID);
		if(coach == null)
			return false;
		view.updateCoachPanel(coach);
		return true;
	}

	public void updateCoach(Coach updatedCoach) {
		Coach coach = coachModel.updateCoach(updatedCoach);
		String status;
		if(updatedCoach.isInGameStatus())
			status = "current";
		else
			status = "past";
		view.viewCoachPanel(coach, status, "home");
	}

	public boolean deleteCoachView(int ID) {
		Coach coach = coachModel.searchCoach(ID);
		if(coach == null) return false;
		view.deleteCoachPanel(coach);
		return true;
	}

	public LinkedHashMap<String, String> getCoachInformation(String status) {
		LinkedHashMap<String, String> info = new LinkedHashMap<>();

		for(Coach c : coachModel.getAllCoaches()) {
			if(c == null)
				continue;

			String key = String.valueOf(c.getCoachID());
			key += "-";
			if(c.isInGameStatus())
				key += "current";
			else
				key += "past";
			if(status.equals("current") && !c.isInGameStatus())
				continue;

			if(status.equals("past") && c.isInGameStatus())
				continue;

			String value = c.getInfo();
			info.put(key, value);
			System.out.println(c.getInfo());
		}

		return info;
	}

	public void deleteCoach(Coach coach) {
		coachModel.deleteCoach(coach.getCoachID());
	}

	// ---------------------------------------------

	//PLAYER===========================================
	public ArrayList<Player> getPlayerInformation() {

		ArrayList<Player> players = new ArrayList<>();
        for(Player p : playerModel.getAllPlayers()){
			if(p == null) {continue;}
			players.add(p);
		}
		players.sort(Comparator.comparingInt(Player::getPlayerID));
		return players;
	}

	public void addPlayer(Player player) {
		playerModel.addPlayer(player);
	}

	public void togglePlayer(Player player) {
		playerModel.togglePlayer(player.getPlayerID());
	}

	public void showPlayer(int ID) {
		Player player = playerModel.searchPlayer(ID);
		view.viewPlayerPanel(player);
	}

	public boolean updatePlayerView(int ID) {
		Player player = playerModel.searchPlayer(ID);
		if(player == null) return false; 
		view.updatePlayerPanel(player);
		return true;
	}
	
	public void updatePlayer(Player updatedPlayer) {
		Player player = playerModel.updatePlayer(updatedPlayer);
		view.viewPlayerPanel(player);
	}
		
	public boolean deletePlayerView(int ID) {
		Player player = playerModel.searchPlayer(ID);
		if(player == null) return false; 
		view.deletePlayerPanel(player);
		return true;
	}
	
	public void deletePlayer(Player player) {
		playerModel.deletePlayer(player.getPlayerID());
	}

	public int generatePlayerID() {
		return playerModel.generatePlayerID();
	}
    public void handlePlayer(String option) {
		switch(option) {
			case "home":
				handleMenu("player");
				break;
			case "create":
				view.createPlayerPanel();
				break;
			case "viewAll":
				view.viewAllPlayerPanel(getPlayerInformation());
				break;
			case "update":
				view.searchUpdatePlayerPanel();
				break;
			case "delete":
				view.searchDeletePlayerPanel();
				break;
			case "report":
				view.viewReportPlayerPanel();
				break;
			case "exit":
				view.dashboardPanel();
				break;
		}
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
        tournamentModel.addTournament(tournament);
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


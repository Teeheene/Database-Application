package model;

import util.*;

public class Tournament {

    private int tournamentID;
    private String tournamentName;
    private int seasonYear;
    private String tournamentType;
    private CustomTimestamp startDate;
    private CustomTimestamp endDate;

    // Constructor
    public Tournament(int tournamentID, String tournamentName, int seasonYear, 
                      String tournamentType, CustomTimestamp startDate, CustomTimestamp endDate) {

        this.tournamentID = tournamentID;
        this.tournamentName = tournamentName;
        this.seasonYear = seasonYear;
        this.tournamentType = tournamentType;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    /*
    Update method -- NEED TO FIX
     */
    public void update(Tournament updatedTournament) {
        this.tournamentName = updatedTournament.getTournamentName();
        this.tournamentType = updatedTournament.getTournamentType();
        this.startDate = updatedTournament.getStartDate();
        this.endDate = updatedTournament.getEndDate();
    }


    /*
    Getters
     */
    public int getTournamentID() { return tournamentID; }
    public String getTournamentName() { return tournamentName; }
    public int getSeasonYear() { return seasonYear; }
    public String getTournamentType() { return tournamentType; }
    public CustomTimestamp getStartDate() { return startDate; }
    public CustomTimestamp getEndDate() { return endDate; }

    /*
    Setters
     */
    public void setTournamentID(int tournamentID) { this.tournamentID = tournamentID; }
    public void setTournamentName(String tournamentName) { this.tournamentName = tournamentName; }
    public void setSeasonYear(int seasonYear) { this.seasonYear = seasonYear; }
    public void setTournamentType(String tournamentType) { this.tournamentType = tournamentType; }
    public void setStartDate(CustomTimestamp startDate) { this.startDate = startDate; }
    public void setEndDate(CustomTimestamp endDate) { this.endDate = endDate; }

}



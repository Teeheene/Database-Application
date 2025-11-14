package model;

import util.*;
import java.util.*;

public class Game {

    /* draft */
    private int gameID;
    private int tournamentID;
    private int ArrayList<Integer> competingTeamIDs;
    private int winningTeamID;
    private int losingTeamID;
    private int scoreRatio;
    private String gameStatus;
    private CustomTimestamp startDate;
    private CustomTimestamp endDate;

    // constructor
    public Game (int gameID, int tournamentID, int competingTeamIDs, int winningTeamID, int losingTeamID,
                 int scoreRatio, String gameStatus, CustomTimestamp startDate, CustomTimestamp endDate) {
        this.gameID = gameID;
        this.tournamentID = tournamentID;
        this.competingTeamIDs = new ArrayList<Integer>();
        this.winningTeamID = winningTeamID;
        this.losingTeamID = losingTeamID;
        this.scoreRatio = scoreRatio;
        this.gameStatus = gameStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    /*
    Getters
    */
    public int getGameID() { return gameID; }
    public int getTournamentID() { return tournamentID; }
    public ArrayList<Integer> getCompetingTeamIDs() { return competingTeamIDs; }
    public int getWinningTeamID() { return winningTeamID; }
    public int getLosingTeamID() { return losingTeamID; }
    public int getScoreRatio() { return scoreRatio; }
    public String getGameStatus() { return gameStatus; }
    public CustomTimestamp getStartDate() { return startDate; }
    public CustomTimestamp getEndDate() { return endDate; }

    /*
    Setters
    */
    public void setGameID(int gameID) { this.gameID = gameID; }
    public void setTournamentID(int tournamentID) { this.tournamentID = tournamentID; }
    public void setWinningTeamID(int winningTeamID) { this.winningTeamID = winningTeamID; }
    public void setLosingTeamID(int losingTeamID) { this.losingTeamID = losingTeamID; }
    public void setScoreRatio(int scoreRatio) { this.scoreRatio = scoreRatio; }
    public void setGameStatus(String gameStatus) { this.gameStatus = gameStatus; }
    public void setStartDate(CustomTimestamp startDate) { this.startDate = startDate; }
    public void setEndDate(CustomTimestamp endDate) { this.endDate = endDate; }

}

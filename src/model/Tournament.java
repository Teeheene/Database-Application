package model;


public class Tournament {

    private int tournamentID;
    private String tournamentName;
    private int seasonYear;
    private String tournamentType;
    private Timestamp startDate;
    private Timestamp endDate;
    // not sure with team bracket (attribute type)
    private String teamBracket;
    private String fanFavoriteTeam;

    // Constructor
    public Tournament(int tournamentID, String tournamentName, int seasonYear, String tournamentType,
                      Timestamp startDate, Timestamp endDate, String teamBracket, String fanFavoriteTeam) {

        this.tournamentID = tournamentID;
        this.tournamentName = tournamentName;
        this.seasonYear = seasonYear;
        this.tournamentType = tournamentType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teamBracket = teamBracket;
        this.fanFavoriteTeam = fanFavoriteTeam;

    }
    /*
    Getters
     */

    public int getTournamentID() { return tournamentID; }
    public String getTournamentName() { return tournamentName; }
    public int getSeasonYear() { return seasonYear; }
    public String getTournamentType() { return tournamentType; }
    public Timestamp getStartDate() { return startDate; }
    public Timestamp getEndDate() { return endDate; }
    public String getTeamBracket() { return teamBracket; }
    public String getFanFavoriteTeam() { return fanFavoriteTeam; }

    /*
    Setters
     */

    public void setTournamentName(String tournamentName) { this.tournamentName = tournamentName; }
    public void setTournamentType(String tournamentType) { this.tournamentType = tournamentType; }
    public void setStartDate(Timestamp startDate) { this.startDate = startDate; }
    public void setEndDate(Timestamp endDate) { this.endDate = endDate; }
    public void setTeamBracket(String teamBracket) { this.teamBracket = teamBracket; }
    public void setFanFavoriteTeam(String fanFavoriteTeam) { this.fanFavoriteTeam = fanFavoriteTeam; }
    
}


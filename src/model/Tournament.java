package model;


public class Tournament {

    private int tournamentID;
    private String tournamentName;
    private int seasonYear;
    private String tournamentType;
    private Timestamp startDate;
    private Timestamp endDate;

    // Constructor
    public Tournament(int tournamentID, String tournamentName, int seasonYear, 
                      String tournamentType, Timestamp startDate, Timestamp endDate) {

        this.tournamentID = tournamentID;
        this.tournamentName = tournamentName;
        this.seasonYear = seasonYear;
        this.tournamentType = tournamentType;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    /*
    Update method
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
    public Timestamp getStartDate() { return startDate; }
    public Timestamp getEndDate() { return endDate; }

    /*
    Setters
     */

    public void setTournamentName(String tournamentName) { this.tournamentName = tournamentName; }
    public void setTournamentType(String tournamentType) { this.tournamentType = tournamentType; }
    public void setStartDate(Timestamp startDate) { this.startDate = startDate; }
    public void setEndDate(Timestamp endDate) { this.endDate = endDate; }

}



package model;
import java.util.ArrayList;

public class Coach {
    private int coachID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String birthday;
    private char gender;
    private int startYear;
    private int endYear;
    private int yearsInField;
    private boolean inGameStatus;
    private ArrayList<Player> team;

    public Coach(int coachID, String lastName, String firstName, String middleName,
                 String birthday, char gender, int startYear, int endYear,
                 boolean inGameStatus) {
        this.coachID = coachID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.gender = gender;
        this.startYear = startYear;
        this.endYear = endYear;
        this.yearsInField = this.endYear - this.startYear;
        this.inGameStatus = inGameStatus;
        this.team = new ArrayList<>();
    }

    public void update(Coach other) {
        if(other.getFirstName() != null) { this.firstName = other.getFirstName(); }
        if(other.getMiddleName() != null) { this.middleName = other.getMiddleName(); }
        if(other.getLastName() != null) { this.lastName = other.getLastName(); }
        if(other.getBirthday() != null) { this.birthday = other.getBirthday(); }
        if(other.getGender() != '\0') { this.gender = other.getGender(); }
        this.inGameStatus = other.isInGameStatus();
    }

    public void addPlayerToTeam(Player player) {
        if(player != null)
            team.add(player);
    }

    public int getCoachID() { return coachID; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
    public String getBirthday() { return birthday; }
    public char getGender() { return gender; }
    public int getStartYear() { return startYear; }
    public int getEndYear() { return endYear; }
    public int getYearsInField() { return yearsInField; }
    public boolean isInGameStatus() { return inGameStatus; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setBirthday(String birthday) { this.birthday = birthday; }
    public void setGender(char gender) { this.gender = gender; }
    public void setInGameStatus(boolean inGameStatus) { this.inGameStatus = inGameStatus; }
}

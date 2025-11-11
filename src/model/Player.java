package model;
public class Player
{

    private int PlayerID;
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthday;
    private char gender;
    private double height;
    private double weight;
    private boolean rStatus;
    
    //Constructor
    public Player(int PlayerID, String lastName, String firstName, String middleName, String birthday, char gender, double height, double weight, boolean rStatus){
    
    this.PlayerID = PlayerID;
    this.lastName = lastName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.birthday = birthday;
    this.gender = gender;
    this.height = height;
    this.weight = weight;
    this.rStatus = rStatus;
    }

    public void update(Player other){
        if(other.getLastName() != null){this.lastName = other.getLastName();}
        if(other.getFirstName() != null){this.firstName = other.getFirstName();}
        if(other.getMiddleName() != null){this.middleName= other.getMiddleName();}
        if(other.getDateofBirth() != null) { this.birthday = other.getDateofBirth(); }
		if(other.getGender() != '\u0000'){ this.gender = other.getGender(); }
        this.rStatus = other.getStatus();
    }
    //Getters
    public int getPlayerID(){return PlayerID;}
    public String getLastName() { return lastName; }
	public String getFirstName() { return firstName; }
	public String getMiddleName() { return middleName; }
    public int getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE; 
        
        try {
            LocalDate birthDate = LocalDate.parse(this.birthday, formatter);
            
            LocalDate currentDate = LocalDate.now();
            

            return Period.between(birthDate, currentDate).getYears();
        } catch (Exception e) {
            System.err.println("Error calculating age for PlayerID " + this.PlayerID + ": " + e.getMessage());
            return -1; // Return a sentinel value like -1 to indicate an error
        }
    }
	
	public String getDateofBirth() { return birthday; }
	public char getGender() { return gender; }
    public double getHeight() { return height; }
	public double getWeight() { return weight; }
	public boolean getStatus() { return rStatus; }

    //Setters
    public void setLastName(String lastName) { this.lastName = lastName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setMiddleName(String middleName) { this.middleName = middleName; }
	public void setDateOfBirth(String dateOfBirth) { this.birthday = birthday; }
	public void setSex(char sex) { this.gender = sex; }

}

package model;

import util.*;
public class Player
{

    private int PlayerID;
    private String lastName;
    private String firstName;
    private String middleName;
    private CustomTimestamp birthday;
    private String gender;
    private double height;
    private double weight;
    private boolean rStatus;
    
    //Constructor
    public Player(int PlayerID, String lastName, String firstName, String middleName,
                String birthday, String gender, double height, double weight, boolean rStatus){
 
		 this.PlayerID = PlayerID;
		 this.lastName = lastName;
		 this.firstName = firstName;
		 this.middleName = middleName;
		 this.gender = gender;
		 this.height = height;
		 this.weight = weight;
		 this.rStatus = rStatus;

		String[] dateTokens = birthday.split("-");
		this.birthday = new CustomTimestamp(
			Integer.parseInt(dateTokens[0]), 
			Integer.parseInt(dateTokens[1]), 
			Integer.parseInt(dateTokens[2])
		);
   }

    public void update(Player other){
        if(other.getLastName() != null){this.lastName = other.getLastName();}
        if(other.getFirstName() != null){this.firstName = other.getFirstName();}
        if(other.getMiddleName() != null){this.middleName= other.getMiddleName();}
        if(other.getDateofBirth() != null) { this.birthday = other.getDateofBirth(); }
		if(other.getGender() != null){ this.gender = other.getGender(); }
       this.rStatus = other.getStatus();
    }
	
	public String toHtmlString() {
		String info = "<html>I’m born on " + birthday.getDisplayDate() + " and I am a " + gender +" basketball player<br>#" + height + "cm #" + weight + "kg #";  
		if(rStatus)
			info += "CurrentlyActive";
		else
			info += "Inactive";
		info += "</html>";

		return info;
	}

    //Getters
    public int getPlayerID(){return PlayerID;}
    public String getLastName() { return lastName; }
	public String getFirstName() { return firstName; }
	public String getMiddleName() { return middleName; }
	/** Returns the birthday as a String in "YYYY-MM-DD" format. Required by GUI. */
    public String getDateOfBirth() {return birthday.toStringDate();} 
    
    /** Returns the CustomTimestamp object. Used in the update method. */
    public CustomTimestamp getBirthday() { 
        return birthday; 
    } 
	public String getGender() { return gender; }
   public double getHeight() { return height; }
	public double getWeight() { return weight; }
	public boolean getStatus() { return rStatus; }
    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        if (firstName != null && !firstName.isEmpty()) {
            fullName.append(firstName).append(" ");
        }
        if (middleName != null && !middleName.isEmpty()) {
            fullName.append(middleName).append(" ");
        }
        if (lastName != null && !lastName.isEmpty()) {
            fullName.append(lastName);
        }
        return fullName.toString().trim();
    }
	 public CustomTimestamp getDateofBirth() { return birthday; }

    //Setters
	public void setPlayerID(int playerID) { this.PlayerID = playerID; } 
	public void setHeight(int height){this.height = height;}
    public void setWeight(int weight){this.weight = weight;}
    public void setLastName(String lastName) { this.lastName = lastName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setMiddleName(String middleName) { this.middleName = middleName; }
	public void setDateOfBirth(String dateOfBirth) { 
        // Logic to convert String date (YYYY-MM-DD) back into CustomTimestamp
        String[] dateTokens = dateOfBirth.split("-");
        this.birthday = new CustomTimestamp(
            Integer.parseInt(dateTokens[0]), 
            Integer.parseInt(dateTokens[1]), 
            Integer.parseInt(dateTokens[2])
        );
    } 
	public void setDateOfBirth(CustomTimestamp dateOfBirth) { this.birthday = birthday; }
	public void setSex(String sex) { this.gender = sex; }
}


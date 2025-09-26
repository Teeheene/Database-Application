package model;

import view.LoginBuilder;

/*
 *	Make a builder for enthusiast :D
 * */
public class Enthusiast {
	private static int nextID = 1;
	private int ID;
	private String username;
	private String lastName;
	private String firstName;
	private String middleName;
	private String dateOfBirth;	
	private String sex;
	private String joinDate;
	//temporary until IDs are implemented for other classes
	private String favoritePlayer;

	//login details
	private LoginBuilder loginDetails;

	public Enthusiast() {
		//empty constructor
	};

	public Enthusiast(String username, String lastName, String firstName, String middleName, String dateOfBirth, String sex, String password) {
		this.ID = nextID++;
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.joinDate = joinDate;
		this.loginDetails = new LoginBuilder(username, password, "enthusiast"); 
	}

	public void update(Enthusiast other) {
		if(other.getUsername() != null) { this.username = other.getUsername(); }
		if(other.getLastName() != null) { this.lastName = other.getLastName(); }
		if(other.getFirstName() != null) { this.firstName = other.getFirstName(); }
		if(other.getMiddleName() != null) { this.middleName = other.getMiddleName(); }
		if(other.getDateOfBirth() != null) { this.dateOfBirth = other.getDateOfBirth(); }
		if(other.getSex() != null) { this.sex = other.getSex(); }
	}

	public int getID() { return ID; }
	public String getUsername() { return username; }
	public String getLastName() { return lastName; }
	public String getFirstName() { return firstName; }
	public String getMiddleName() { return middleName; }
	public String getDateOfBirth() { return dateOfBirth; }
	public String getSex() { return sex; }
	public String getJoinDate() { return joinDate; }
	public String getFavoritePlayer() { return favoritePlayer; }
	public String getPassword() { return loginDetails.getPassword(); } 

	public void setUsername(String username) { this.username = username; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public void setMiddleName(String middleName) { this.middleName = middleName; }
	public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
	public void setSex(String sex) { this.sex = sex; }
	public void setFavoritePlayer(String favoritePlayer) { this.favoritePlayer = favoritePlayer; }
}
